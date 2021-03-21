package br.com.b2w.port.adapters.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.b2w.domain.Planet;
import br.com.b2w.domain.PlanetRepository;
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
			.post("/planet")
			.then()
			.statusCode(201);
	}

	@Test
	public void getPlanetByNameEndpoint() {
		
		given()
			.when()
				.get("/planet/Tatooine")
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
				.get("/planet?id=b7b50b31-94c6-4e5f-94b4-1bf1edbcc156")
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
				.delete("/planet/b7b50b31-94c6-4e5f-94b4-1bf1edbcc156")
					.then()
					.statusCode(204);
	}

}
