package br.com.tiagolivrera.IMDbMovies.view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import br.com.tiagolivrera.IMDbMovies.models.MovieRecord;

public class HTMLGenerator {

	public static void generate(List<MovieRecord> listMovies) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("movies.html"))) {
			writer.write("<!DOCTYPE html>");
			writer.newLine();
			writer.write("<html>");
			writer.newLine();
			writer.write("<head>");
			writer.newLine();
			writer.write("<title>Lista de Filmes</title>");
			writer.newLine();
			writer.write("</head>");
			writer.newLine();
			writer.write("<body>");
			writer.newLine();
			writer.write("<h1>Lista de Filmes</h1>");
			writer.newLine();
			writer.write("<table>");
			writer.newLine();
			writer.write("<tr>");
			writer.newLine();
			writer.write("<th>Título</th>");
			writer.newLine();
			writer.write("<th>Capa</th>");
			writer.newLine();
			writer.write("<th>Data de Lançamento</th>");
			writer.newLine();
			writer.write("</tr>");
			writer.newLine();
			for (MovieRecord movie : listMovies) {
				writer.write("<tr>");
				writer.newLine();
				writer.write("<td>" + movie.title() + "<td>");
				writer.newLine();
				if (movie.url() != null) {
					writer.write("<td><img src=\"" + movie.url() + "\" alt=\"Capa do Filme\" width=\"500\" height=\"600\"></td>");
					writer.newLine();
				}
				writer.write("<td>" + " Lançamento: " + movie.releaseDate().toString() + "</td>");
				writer.newLine();
				writer.write("</tr>");
				writer.newLine();
			}
			writer.write("</table>");
			writer.newLine();
			writer.write("</body>");
			writer.newLine();
			writer.write("</html>");
			writer.newLine();

			System.out.println("Arquivo HTML gerado com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
