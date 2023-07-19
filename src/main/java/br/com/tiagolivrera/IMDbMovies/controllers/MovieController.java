package br.com.tiagolivrera.IMDbMovies.controllers;

import java.io.IOException;
import java.util.List;

import br.com.tiagolivrera.IMDbMovies.models.MovieRecord;
import br.com.tiagolivrera.IMDbMovies.services.ParsingMovieService;

public class MovieController {

	private String API_URL;

	public MovieController(String API_URL) {
		this.API_URL = API_URL;
	}

	public List<MovieRecord> getMovies() throws IOException, InterruptedException {
		ParsingMovieService movieService = new ParsingMovieService(API_URL);
		return movieService.getListMovies();
	}

}
