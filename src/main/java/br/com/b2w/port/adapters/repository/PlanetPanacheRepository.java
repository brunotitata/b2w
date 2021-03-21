package br.com.b2w.port.adapters.repository;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import br.com.b2w.domain.Planet;
import br.com.b2w.domain.PlanetRepository;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

@ApplicationScoped
public class PlanetPanacheRepository implements PanacheMongoRepository<Planet>, PlanetRepository {

	@Override
	public void save(Planet planet) {
		persist(planet);
	}

	@Override
	public Optional<Planet> findByPlanet(String name) {
		return find("planetName", name).firstResultOptional();
	}

	@Override
	public Optional<Planet> getPlanetByPlanetId(String planetId) {
		return find("planetId", planetId).firstResultOptional();
	}

	@Override
	public void delete(String planetId) {
		delete("planetId", planetId);
		
	}

	@Override
	public void deleteByName(String name) {
		delete("planetName", name);
	}

	@Override
	public List<Planet> listAllPlanets() {
		return findAll().list();
	}


}
