package br.com.tiagolivrera.IMDbMovies;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class App {
	public static void main(String[] args) throws IOException, InterruptedException {
		ConsumerRequest cr = new ConsumerRequest();
		File file = new File("out.json");
		PrintStream printStream = new PrintStream(new FileOutputStream(file));
		System.setOut(printStream);
		System.out.println(cr.getUpcomingMovies());
	}
}
