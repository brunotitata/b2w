package br.com.b2w.port.adapters.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.b2w.domain.Planet;
import br.com.b2w.domain.PlanetRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class PlanetRepositoryTest {

	@InjectMock
	private PlanetPanacheRepository repository;
	
	@InjectMock
	private PlanetRepository planetRepository;
	
	@Test
	public void save() {
		
		var planet = new Planet("b7b50b31-94c6-4e5f-94b4-1bf1edbcc156", 
				"Tatooine", 
				"clima", 
				"terra", 
				5);
		
		planetRepository.save(planet);
		
		verify(repository, times(1)).save(planet);
	}

	@Test
	public void findByPlanet() {
		
		Mockito.when(repository.findByPlanet("Tatooine")).thenReturn(Optional.ofNullable(new Planet(
				"b7b50b31-94c6-4e5f-94b4-1bf1edbcc156", 
				"Tatooine", 
				"clima", 
				"terra", 
				5)));
		
		var planet = planetRepository.findByPlanet("Tatooine");
		
		assertAll(
				() -> assertEquals("b7b50b31-94c6-4e5f-94b4-1bf1edbcc156", planet.get().getPlanetId()),
				() -> assertEquals("Tatooine", planet.get().getPlanetName()),
				() -> assertEquals("clima", planet.get().getClimate()),
				() -> assertEquals("terra", planet.get().getTerrain()));

	}
	
	@Test
	public void getPlanetByPlanetId() {
		
		Mockito.when(repository.findByPlanet("Tatooine")).thenReturn(Optional.ofNullable(new Planet(
				"b7b50b31-94c6-4e5f-94b4-1bf1edbcc156", 
				"Tatooine", 
				"clima", 
				"terra", 
				5)));
		
		var planet = planetRepository.findByPlanet("Tatooine");
		
		assertAll(
				() -> assertEquals("b7b50b31-94c6-4e5f-94b4-1bf1edbcc156", planet.get().getPlanetId()),
				() -> assertEquals("Tatooine", planet.get().getPlanetName()),
				() -> assertEquals("clima", planet.get().getClimate()),
				() -> assertEquals("terra", planet.get().getTerrain()));

	}
	
	@Test
	public void deleteByName() {
		
		planetRepository.deleteByName("Tatooine");
		
		verify(repository, times(1)).deleteByName("Tatooine");

	}
	
	@Test
	public void delete() {
		
		planetRepository.delete("b7b50b31-94c6-4e5f-94b4-1bf1edbcc156");
		
		verify(repository, times(1)).delete("b7b50b31-94c6-4e5f-94b4-1bf1edbcc156");

	}

}
