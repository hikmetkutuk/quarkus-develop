package com.develop.resource;

import com.develop.client.TvSeriesClient;
import com.develop.model.TvSeries;
import io.vertx.core.json.JsonArray;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/v1/tvseries")
public class TvSeriesResource {
    @RestClient
    TvSeriesClient tvSeriesClient;

    /**
     * Retrieves a TV series by its ID.
     *
     * @param id the ID of the TV series
     * @return the response containing the TV series
     */
    @GET
    @Path("/list/{id}")
    @Fallback(fallbackMethod = "getTvSeriesByIdFallback")
    public Response getTvSeriesById(@PathParam("id") int id) {
        return Response.ok(tvSeriesClient.getTvSeriesById(id)).build();
    }

    /**
     * Retrieves a TV series by the specified ID, and provides a fallback response if the retrieval fails.
     *
     * @param id the ID of the TV series to retrieve
     * @return the response containing the retrieved TV series, or a fallback response if retrieval fails
     */
    public Response getTvSeriesByIdFallback(int id) {
        return Response.ok(new TvSeries()).build();
    }

    /**
     * Get TV series by person name.
     *
     * @param personName the name of the person
     * @return a JSON array of TV series
     */
    @GET
    @Path("/person/{personName}")
    public JsonArray getTvSeriesByPerson(@PathParam("personName") String personName) {
        return tvSeriesClient.getTvSeriesByPerson(personName);
    }
}
