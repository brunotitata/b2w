package br.com.b2w.domain;

import java.util.List;
import java.util.Optional;

public interface PlanetRepository {

	void save(Planet planet);

	Optional<Planet> findByPlanet(String name);
	
	Optional<Planet> getPlanetByPlanetId(String planetId);
	
	void delete(String planetId);

	void deleteByName(String name);
	
	List<Planet> listAllPlanets();

}
