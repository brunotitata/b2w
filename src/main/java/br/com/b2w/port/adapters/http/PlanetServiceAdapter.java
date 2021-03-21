package br.com.b2w.port.adapters.http;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

@ApplicationScoped
@RegisterRestClient(baseUri = "https://swapi.dev/api/planets/")
public interface PlanetServiceAdapter {

	@GET
	@Produces("application/json")
	SwapiResource getByPlanet(@QueryParam(value = "search") String planet);
}
