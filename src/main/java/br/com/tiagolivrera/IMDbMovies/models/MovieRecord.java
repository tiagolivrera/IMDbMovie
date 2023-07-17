package br.com.tiagolivrera.IMDbMovies.models;

import java.time.LocalDate;

public record MovieRecord(String title, String url, LocalDate releaseDate) {
}
