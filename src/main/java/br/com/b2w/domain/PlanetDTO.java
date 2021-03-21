package br.com.b2w.domain;

public class PlanetDTO {

	private String planetName;
	private String climate;
	private String terrain;

	public PlanetDTO(String planetName, String climate, String terrain) {
		this.planetName = planetName;
		this.climate = climate;
		this.terrain = terrain;
	}

	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
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

	@Override
	public String toString() {
		return "PlanetDTO [planetName=" + planetName + ", climate=" + climate + ", terrain=" + terrain + "]";
	}

}
