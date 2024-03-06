package com.develop.client;

import com.develop.model.TvSeries;
import io.vertx.core.json.JsonArray;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/")
@RegisterRestClient(baseUri = "https://api.tvmaze.com")
public interface TvSeriesClient {

    @GET
    @Path("/shows/{id}")
    TvSeries getTvSeriesById(@PathParam("id") int id);

    @GET
    @Path("/search/people")
    JsonArray getTvSeriesByPerson(@QueryParam("q") String personName);
}
