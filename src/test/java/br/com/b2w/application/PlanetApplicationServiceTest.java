package br.com.b2w.application;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Optional;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import br.com.b2w.domain.Planet;
import br.com.b2w.domain.PlanetDTO;
import br.com.b2w.domain.PlanetRepository;
import br.com.b2w.domain.PlanetResponseDTO;
import br.com.b2w.port.adapters.http.FilmResource;
import br.com.b2w.port.adapters.http.PlanetServiceAdapter;
import br.com.b2w.port.adapters.http.SwapiResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class PlanetApplicationServiceTest {
	
	private PlanetApplicationService applicationService;
	
	@InjectMock
	private PlanetRepository planetRepository;
	
	@InjectMock
	@RestClient
	private PlanetServiceAdapter planetServiceAdapter;
	
	@BeforeEach
	public void setUp() {
		this.applicationService = new PlanetApplicationService(planetServiceAdapter, planetRepository);
	}
	
	@Test
	public void createPlanet() {
		
		SwapiResource swapiResource = mock(SwapiResource.class);
		
		Mockito.when(planetServiceAdapter.getByPlanet("Tatooine")).thenReturn(swapiResource);
		Mockito.when(swapiResource.getResults()).thenReturn(Arrays.asList(new FilmResource(
				Arrays.asList("http://swapi.dev/api/films/1/", 
		                "http://swapi.dev/api/films/3/", 
		                "http://swapi.dev/api/films/4/", 
		                "http://swapi.dev/api/films/5/", 
		                "http://swapi.dev/api/films/6/"))));
		
		String planetId = applicationService.createPlanet(new PlanetDTO(
				"Tatooine", 
				"arid", 
				"desert"));
		
		ArgumentCaptor<Planet> argumentCaptor = ArgumentCaptor.forClass(Planet.class);
		
		verify(planetRepository).save(argumentCaptor.capture());
		
		Planet planet = argumentCaptor.getValue();
		
		assertAll(
				() -> assertNotNull(planetId),
				() -> assertEquals("Tatooine", planet.getPlanetName()),
				() -> assertEquals("arid", planet.getClimate()),
				() -> assertEquals("desert", planet.getTerrain()),
				() -> assertEquals(5, planet.getAmountOfFilmAppearances()));
	}
	
	@Test
	public void getPlanetByName() {
		
		Mockito.when(planetRepository.findByPlanet("Tatooine")).thenReturn(Optional.ofNullable(new Planet(
				"b7b50b31-94c6-4e5f-94b4-1bf1edbcc156", 
				"Tatooine", 
				"arid", 
				"desert", 
				5)));
		
		PlanetResponseDTO planet = applicationService.getPlanetByName("Tatooine");
		
		assertAll(
				() -> assertEquals("b7b50b31-94c6-4e5f-94b4-1bf1edbcc156", planet.getPlanetId()),
				() -> assertEquals("Tatooine", planet.getName()),
				() -> assertEquals("arid", planet.getClimate()),
				() -> assertEquals("desert", planet.getTerrain()),
				() -> assertEquals(5, planet.getAmountOfFilmAppearances()));
	}
	
	@Test
	public void whenTheSearchDoesNotFindPlanetShouldReturnRuntimeException() {
		
		Mockito.when(planetRepository.findByPlanet("Tatooineeee")).thenThrow(new RuntimeException("planet not found with name: Tatooineeee"));
		
		assertThrows(RuntimeException.class, 
				() -> applicationService.getPlanetByName("Tatooineeee"),
				"planet not found with name: Tatooineeee");
	}
	
	@Test
	public void getPlanetByPlanetId() {
		
		Mockito.when(planetRepository.getPlanetByPlanetId("b7b50b31-94c6-4e5f-94b4-1bf1edbcc156")).thenReturn(Optional.ofNullable(new Planet(
				"b7b50b31-94c6-4e5f-94b4-1bf1edbcc156", 
				"Tatooine", 
				"arid", 
				"desert", 
				5)));
		
		PlanetResponseDTO planet = applicationService.getPlanetByPlanetId("b7b50b31-94c6-4e5f-94b4-1bf1edbcc156");
		
		assertAll(
				() -> assertEquals("b7b50b31-94c6-4e5f-94b4-1bf1edbcc156", planet.getPlanetId()),
				() -> assertEquals("Tatooine", planet.getName()),
				() -> assertEquals("arid", planet.getClimate()),
				() -> assertEquals("desert", planet.getTerrain()),
				() -> assertEquals(5, planet.getAmountOfFilmAppearances()));
	}
	
	@Test
	public void whenTheSearchDoesNotFindPlanetByPlanetIdShouldReturnRuntimeException() {
		
		Mockito.when(planetRepository.getPlanetByPlanetId("b7b50b31-94c6-4e5f-94b4-1bf1edbcc156")).thenThrow(new RuntimeException("planet not found with name: Tatooineeee"));
		
		assertThrows(RuntimeException.class, 
				() -> applicationService.getPlanetByName("Tatooineeee"),
				"planet not found with planetId: b7b50b31-94c6-4e5f-94b4-1bf1edbcc156");
	}
	
	@Test
	public void deletePlanetByPlanetId() {
		
		applicationService.deletePlanetByPlanetId("b7b50b31-94c6-4e5f-94b4-1bf1edbcc156");
		
		verify(planetRepository, times(1)).delete("b7b50b31-94c6-4e5f-94b4-1bf1edbcc156");
	}

}
