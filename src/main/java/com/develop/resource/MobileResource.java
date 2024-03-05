package com.develop.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/api/v1/mobile")
public class MobileResource {
    List<String> mobileList = new ArrayList<>();

    /**
     * Retrieves the list of mobile devices.
     * This method handles GET requests to "/api/v1/mobile/list" endpoint.
     * It produces a response in plain text format.
     *
     * @return The list of mobile devices.
     */
    @GET
    @Path("/list")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMobileList() {
        return Response.ok(mobileList).build();
    }

    /**
     * Adds a new mobile device to the list.
     * This method handles POST requests to "/api/v1/mobile/create" endpoint.
     * It consumes plain text format.
     *
     * @param mobileName The name of the mobile device to be added.
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response addMobile(String mobileName) {
        mobileList.add(mobileName);
        return Response.ok(mobileName).build();
    }

    /**
     * Deletes a mobile device from the list.
     * This method handles DELETE requests to "/api/v1/mobile/delete/{mobile}" endpoint.
     * It consumes plain text format.
     *
     * @param mobileName The name of the mobile device to be deleted.
     * @return The updated list of mobile devices.
     */
    @DELETE
    @Path("/delete/{mobile}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response deleteMobile(@PathParam("mobile") String mobileName) {
        boolean isDeleted = mobileList.remove(mobileName);
        if (isDeleted) {
            return Response.ok(mobileList).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
