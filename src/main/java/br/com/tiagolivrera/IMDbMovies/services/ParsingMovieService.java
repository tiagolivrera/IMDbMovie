package br.com.tiagolivrera.IMDbMovies.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.tiagolivrera.IMDbMovies.consumer.ConsumerRequest;
import br.com.tiagolivrera.IMDbMovies.models.MovieRecord;

public class ParsingMovieService {

	private String request;

	public ParsingMovieService() throws IOException, InterruptedException {
		ConsumerRequest cr = new ConsumerRequest();
		this.request = cr.getUpcomingMovies();
	}

	public List<MovieRecord> getListMovies() {
		// List<Movie> listMovies = new ArrayList<Movie>();
		List<MovieRecord> listMovies = new ArrayList<MovieRecord>();

		int startIndex = this.request.indexOf("\"results\":[{") + "\"results\":[{".length();
		int endIndex = this.request.lastIndexOf("}]");
		String results = this.request.substring(startIndex, endIndex);

		String[] entries = results.split("\\},\\{");

		for (String entry : entries) {
			String url;
			String title;
			Integer day, month, year;

			// url
			if (!entry.contains("\"url\":"))
				url = null;
			else {
				int urlStartIndex = entry.indexOf("\"url\":") + "\"url\":\"".length();
				int urlEndIndex = entry.indexOf("\",", urlStartIndex);
				url = entry.substring(urlStartIndex, urlEndIndex);
			}

			// title
			if (!entry.contains("\"titleText\":"))
				title = null;
			else {
				int titleStartIndex = entry.indexOf("\"titleText\":") + "\"titleText\":{\"text\":\"".length();
				int titleEndIndex = entry.indexOf("\",", titleStartIndex);
				title = entry.substring(titleStartIndex, titleEndIndex);
			}

			// releaseDate
			if (!entry.contains("\"releaseDate\":")) {
				day = -1;
				month = -1;
				year = -1;
			} else {
				int dayStartIndex = entry.indexOf("\"releaseDate\":{\"day\":") + "\"releaseDate\":{\"day\":".length();
				int dayEndIndex = entry.indexOf(",", dayStartIndex);
				day = Integer.parseInt(entry.substring(dayStartIndex, dayEndIndex).trim());

				int monthStartIndex = entry.indexOf("\"month\":", dayEndIndex) + "\"month\":".length();
				int monthEndIndex = entry.indexOf(",", monthStartIndex);
				month = Integer.parseInt(entry.substring(monthStartIndex, monthEndIndex).trim());

				int yearStartIndex = entry.indexOf("\"year\":", monthEndIndex) + "\"year\":".length();
				int yearEndIndex = entry.indexOf(",", yearStartIndex);
				year = Integer.parseInt(entry.substring(yearStartIndex, yearEndIndex).trim());

			}
			// listMovies.add(new Movie(title, url, day, month, year));
			listMovies.add(new MovieRecord(title, url, LocalDate.of(year, month, day)));
		}
		return listMovies;
	}

}
