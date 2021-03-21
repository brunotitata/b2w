package br.com.b2w.port.adapters.http;

import java.util.List;

public class FilmResource {

	public List<String> films;

	public FilmResource(List<String> films) {
		super();
		this.films = films;
	}
	
	public FilmResource() {
	}

	public List<String> getFilms() {
		return films;
	}

	public void setFilms(List<String> films) {
		this.films = films;
	}

	@Override
	public String toString() {
		return "FilmResource [films=" + films + "]";
	}

}
