package br.com.b2w.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PlanetTest {

	public static String PLANET_ID = "690b9b4a-6fe0-4963-b73c-3aaa0e2f3bef";

	@Test
	public void createPlanet() {

		Planet planet = new Planet(PLANET_ID, "Hoth", "Frozen", "Tundra");

		assertAll(
				() -> assertEquals(PLANET_ID, planet.getPlanetId()),
				() -> assertEquals("Hoth", planet.getPlanetName()), 
				() -> assertEquals("Frozen", planet.getClimate()),
				() -> assertEquals("Tundra", planet.getTerrain()));
	}

	@Test
	public void whenCreatingPlanetWithPlanetIdNullShouldReturnNullPointerException() {

		assertThrows(
				NullPointerException.class, 
				() -> new Planet(null, "Hoth", "Frozen", "Tundra"),
				Planet.PLANET_ID_ERROR_MSG);
	}
	
	@Test
	public void whenCreatingPlanetWithPlanetNameNullShouldReturnNullPointerException() {

		assertThrows(
				RuntimeException.class, 
				() -> new Planet(PLANET_ID, null, "Frozen", "Tundra"),
				Planet.PLANET_NAME_ERROR_MSG);
		
		// adicionando comentario
	}

}
