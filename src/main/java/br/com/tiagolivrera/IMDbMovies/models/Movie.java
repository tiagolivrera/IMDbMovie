package br.com.tiagolivrera.IMDbMovies.models;

import java.time.LocalDate;

public class Movie {

	private String title;
	private String url;
	private LocalDate releaseDate;

	public Movie() {
	}

	public Movie(String title, String url, Integer day, Integer month, Integer year) {
		this.title = title;
		this.url = url;
		this.releaseDate = LocalDate.of(year, month, day);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", url=" + url + ", releaseDate=" + releaseDate + "]";
	}

}
