package br.com.tiagolivrera.IMDbMovies;

import java.io.IOException;
import java.util.List;

import br.com.tiagolivrera.IMDbMovies.models.Movie;
import br.com.tiagolivrera.IMDbMovies.services.ParsingMovieService;

public class App {
	public static void main(String[] args) throws IOException, InterruptedException {

		ParsingMovieService movieService = new ParsingMovieService();
		List<Movie> listMovies = movieService.getListMovies();

		for (Movie m : listMovies) {
			System.out.println(m);
		}
	}
}
