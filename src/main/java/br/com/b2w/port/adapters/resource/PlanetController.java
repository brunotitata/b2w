package br.com.b2w.port.adapters.resource;

import java.util.List;

import javax.ws.rs.core.UriBuilder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.b2w.application.PlanetApplicationService;
import br.com.b2w.domain.PlanetDTO;
import br.com.b2w.domain.PlanetResponseDTO;

@RestController
@RequestMapping("/v1/planet")
public class PlanetController {

	private PlanetApplicationService planetApplicationService;

	public PlanetController(PlanetApplicationService planetApplicationService) {
		this.planetApplicationService = planetApplicationService;
	}

	@PostMapping()
	public ResponseEntity<Void> newPlanet(@RequestBody PlanetDTO planetDTO) {
		
		var planetId = planetApplicationService.createPlanet(planetDTO);
		
		return ResponseEntity.created(
				UriBuilder.fromPath("/v1/planet/{planetId}")
				.build(planetId))
				.build();
	}
	
	@GetMapping
	public ResponseEntity<PlanetResponseDTO> getPlanetByPlanetId(@RequestParam(value = "id", required = true) String planetId) {
		return ResponseEntity.ok(planetApplicationService.getPlanetByPlanetId(planetId));
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<PlanetResponseDTO> getPlanetByName(@PathVariable String name) {
		return ResponseEntity.ok(planetApplicationService.getPlanetByName(name));
	}
	
	@DeleteMapping("/{planetId}")
	public ResponseEntity<Void> delete(@PathVariable String planetId){
		
		planetApplicationService.deletePlanetByPlanetId(planetId);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<PlanetResponseDTO>> all() {
		return ResponseEntity.ok(planetApplicationService.listAllPlanets());
	}

}
