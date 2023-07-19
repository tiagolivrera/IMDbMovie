package br.com.tiagolivrera.IMDbMovies.consumer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumerRequest {

	private String API_KEY;
	private static final String API_URL_UPCOMING = "https://moviesdatabase.p.rapidapi.com/titles/x/upcoming";
	private static final String API_URL = "moviesdatabase.p.rapidapi.com";

	public ConsumerRequest() {
		this.API_KEY = "";
	}

	public ConsumerRequest(String API_KEY) {
		this.API_KEY = API_KEY;
	}

	public String getUpcomingMovies() throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL_UPCOMING))
				.header("X-RapidAPI-Key", API_KEY).header("X-RapidAPI-Host", API_URL)
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}

}
