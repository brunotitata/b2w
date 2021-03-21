package br.com.b2w.port.adapters.http;

import java.util.List;

public class SwapiResource {

	public List<FilmResource> results;

	public SwapiResource(List<FilmResource> results) {
		this.results = results;
	}
	
	public SwapiResource() {
	}

	public List<FilmResource> getResults() {
		return results;
	}

	public void setResults(List<FilmResource> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "SwapiResource [results=" + results + "]";
	}

}
