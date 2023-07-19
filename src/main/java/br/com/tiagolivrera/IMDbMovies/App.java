package br.com.tiagolivrera.IMDbMovies;

import java.io.IOException;

import br.com.tiagolivrera.IMDbMovies.controllers.MovieController;
import br.com.tiagolivrera.IMDbMovies.view.HTMLGenerator;

public class App {
	public static void main(String[] args) throws IOException, InterruptedException {

		MovieController controller = new MovieController("<API_KEY>");

		HTMLGenerator.generate(controller.getMovies());
	}
}
