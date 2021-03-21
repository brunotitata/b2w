package br.com.b2w.domain;

public class PlanetResponseDTO {

	private String planetId;
	private String name;
	private String climate;
	private String terrain;
	private Integer amountOfFilmAppearances;

	public PlanetResponseDTO(String planetId, String name, String climate, String terrain,
			Integer amountOfFilmAppearances) {
		this.planetId = planetId;
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
		this.amountOfFilmAppearances = amountOfFilmAppearances;
	}

	public String getPlanetId() {
		return planetId;
	}

	public void setPlanetId(String planetId) {
		this.planetId = planetId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "PlanetResponseDTO [planetId=" + planetId + ", name=" + name + ", climate=" + climate + ", terrain="
				+ terrain + ", amountOfFilmAppearances=" + amountOfFilmAppearances + "]";
	}

}
