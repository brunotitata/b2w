package br.com.b2w.application;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.b2w.domain.Planet;
import br.com.b2w.domain.PlanetDTO;
import br.com.b2w.domain.PlanetRepository;
import br.com.b2w.domain.PlanetResponseDTO;
import br.com.b2w.port.adapters.http.PlanetServiceAdapter;

@ApplicationScoped
public class PlanetApplicationService {

	private final PlanetServiceAdapter planetServiceAdapter;
	private final PlanetRepository planetRepository;

	public PlanetApplicationService(@RestClient PlanetServiceAdapter planetServiceAdapter,
			PlanetRepository planetRepository) {
		this.planetServiceAdapter = planetServiceAdapter;
		this.planetRepository = planetRepository;
	}

	public String createPlanet(PlanetDTO planetDTO) {

		Long amountAppearances = planetServiceAdapter.getByPlanet(planetDTO.getPlanetName())
				.getResults().stream()
				.map(p -> p.getFilms())
				.flatMap(List::stream).count();

		var planet = new Planet(UUID.randomUUID().toString(), 
				planetDTO.getPlanetName(),
				planetDTO.getClimate(), 
				planetDTO.getTerrain(), 
				amountAppearances.intValue());

		planetRepository.save(planet);

		return planet.getPlanetId();

	}
	
	public PlanetResponseDTO getPlanetByName(String name) {
		return planetRepository.findByPlanet(name)
				.map(Planet::of)
				.orElseThrow(
						() -> new RuntimeException("planet not found with name: " + name));
	}
	
	public PlanetResponseDTO getPlanetByPlanetId(String planetId) {
		return planetRepository.getPlanetByPlanetId(planetId)
				.map(Planet::of)
				.orElseThrow(
						() -> new RuntimeException("planet not found with planetId: " + planetId));
	}
	
	public void deletePlanetByPlanetId(String planetId) {
		planetRepository.delete(planetId);
	}
	
	public List<PlanetResponseDTO> listAllPlanets() {
		return planetRepository.listAllPlanets()
				.stream()
				.map(Planet::of)
				.collect(Collectors.toList());
	}

}
