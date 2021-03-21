package br.com.b2w.port.adapters.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.b2w.domain.Planet;
import br.com.b2w.domain.PlanetRepository;
import br.com.b2w.domain.PlanetResponseDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class PlanetControllerTest {
	
	@Autowired
	private PlanetRepository repository;
	
	@BeforeEach
	public void setUp() {
		repository.save(new Planet(
				"b7b50b31-94c6-4e5f-94b4-1bf1edbcc156", 
				"Tatooine", 
				"arid", 
				"desert", 
				5));
	}
	
	@AfterEach
	public void tearDown() {
		repository.deleteByName("Tatooine");
	}
	
	@Test
	public void newPlanet() {
	
		given()
			.contentType(ContentType.JSON)
			.body("{\n"
					+ "    \"planetName\": \"Tatooine\",\n"
					+ "    \"climate\": \"arid\",\n"
					+ "    \"terrain\": \"desert\"\n"
					+ "}")
			.when()
			.post("/v1/planet")
			.then()
			.statusCode(201);
	}

	@Test
	public void getPlanetByNameEndpoint() {
		
		given()
			.when()
				.get("/v1/planet/Tatooine")
					.then().statusCode(200)
					.body("planetId", is("b7b50b31-94c6-4e5f-94b4-1bf1edbcc156"))
					.body("name", is("Tatooine"))
					.body("climate", is("arid"))
					.body("terrain", is("desert"))
					.body("amountOfFilmAppearances", is(5));
	}
	
	@Test
	public void getPlanetByPlanetIdEndpoint() {
		
		given()
			.when()
				.get("/v1/planet?id=b7b50b31-94c6-4e5f-94b4-1bf1edbcc156")
					.then().statusCode(200)
					.body("planetId", is("b7b50b31-94c6-4e5f-94b4-1bf1edbcc156"))
					.body("name", is("Tatooine"))
					.body("climate", is("arid"))
					.body("terrain", is("desert"))
					.body("amountOfFilmAppearances", is(5));
	}
	
	@Test
	public void deleteEndpoint() {
		
		given()
			.when()
				.delete("/v1/planet/b7b50b31-94c6-4e5f-94b4-1bf1edbcc156")
					.then()
					.statusCode(204);
	}
	
	@Test
	public void all() {
		
		List<PlanetResponseDTO> planets = given()
		.when()
			.get("/v1/planet/all")
				.then()
				.extract()
				.body()
				.jsonPath().getList(".", PlanetResponseDTO.class);
		
		assertAll(
				() -> assertEquals("b7b50b31-94c6-4e5f-94b4-1bf1edbcc156", planets.get(0).getPlanetId()),
				() -> assertEquals("Tatooine", planets.get(0).getName()),
				() -> assertEquals("arid", planets.get(0).getClimate()),
				() -> assertEquals("desert", planets.get(0).getTerrain()));
		
	}

}
