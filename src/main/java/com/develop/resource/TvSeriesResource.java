package com.develop.resource;

import com.develop.client.TvSeriesClient;
import com.develop.model.TvSeries;
import io.vertx.core.json.JsonArray;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/v1/tvseries")
public class TvSeriesResource {
    @RestClient
    TvSeriesClient tvSeriesClient;

    @GET
    @Path("/list/{id}")
    public TvSeries getTvSeriesById(@PathParam("id") int id) {
        return tvSeriesClient.getTvSeriesById(id);
    }

    @GET
    @Path("/person/{personName}")
    public JsonArray getTvSeriesByPerson(@PathParam("personName") String personName) {
        return tvSeriesClient.getTvSeriesByPerson(personName);
    }
}
