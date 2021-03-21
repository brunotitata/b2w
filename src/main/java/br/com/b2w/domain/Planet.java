package br.com.b2w.domain;

import java.util.Objects;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.MongoEntity;

@MongoEntity(collection = "planet")
public class Planet {

	public static final String PLANET_ID_ERROR_MSG = "planetId cannot be null.";
	public static final String PLANET_NAME_ERROR_MSG = "planetName cannot be null.";

	private ObjectId id;
	private String planetId;
	private String planetName;
	private String climate;
	private String terrain;
	private Integer amountOfFilmAppearances;

	public Planet(String planetId, String planetName, String climate, String terrain) {
		setPlanetId(planetId);
		setPlanetName(planetName);
		setClimate(climate);
		setTerrain(terrain);
	}

	public Planet(String planetId, String planetName, String climate, String terrain, Integer amountOfFilmAppearances) {
		setPlanetId(planetId);
		setPlanetName(planetName);
		setClimate(climate);
		setTerrain(terrain);
		setAmountOfFilmAppearances(amountOfFilmAppearances);
	}

	public Planet() {
	}

	public String getPlanetId() {
		return planetId;
	}

	public void setPlanetId(String planetId) {
		this.planetId = Objects.requireNonNull(planetId, PLANET_ID_ERROR_MSG);
	}

	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {

		if (planetName.isBlank() || planetName == null)
			throw new RuntimeException(PLANET_NAME_ERROR_MSG);

		this.planetName = planetName;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public Integer getAmountOfFilmAppearances() {
		return amountOfFilmAppearances;
	}

	public void setAmountOfFilmAppearances(Integer amountOfFilmAppearances) {
		this.amountOfFilmAppearances = amountOfFilmAppearances;
	}

	public ObjectId getId() {
		return id;
	}
	
	public static PlanetResponseDTO of(Planet planet) {
		return new PlanetResponseDTO(
				planet.getPlanetId(),
				planet.getPlanetName(), 
				planet.getClimate(), 
				planet.getTerrain(), 
				planet.getAmountOfFilmAppearances());
	}

	@Override
	public String toString() {
		return "Planet [id=" + id + ", planetId=" + planetId + ", planetName=" + planetName + ", climate=" + climate
				+ ", terrain=" + terrain + ", amountOfFilmAppearances=" + amountOfFilmAppearances + "]";
	}

}
