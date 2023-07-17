package br.com.tiagolivrera.IMDbMovies;

import java.io.IOException;
import java.util.List;

import br.com.tiagolivrera.IMDbMovies.models.MovieRecord;
import br.com.tiagolivrera.IMDbMovies.services.ParsingMovieService;

public class App {
	public static void main(String[] args) throws IOException, InterruptedException {

		ParsingMovieService movieService = new ParsingMovieService();
		List<MovieRecord> listMovies = movieService.getListMovies();

		for (MovieRecord m : listMovies) {
			System.out.println(m);
		}
	}
}
