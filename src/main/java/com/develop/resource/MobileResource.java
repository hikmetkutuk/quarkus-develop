package com.develop.resource;

import com.develop.model.Mobile;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/v1/mobile")
public class MobileResource {
    List<Mobile> mobileList = new ArrayList<>();

    /**
     * Retrieves the list of mobile devices.
     * This method handles GET requests to "/api/v1/mobile/list" endpoint.
     * It produces a response in plain text format.
     *
     * @return The list of mobile devices.
     */
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMobileList() {
        return Response.ok(mobileList).build();
    }

    /**
     * Adds a new mobile device to the list.
     * This method handles POST requests to "/api/v1/mobile/create" endpoint.
     * It consumes plain text format.
     *
     * @param mobile The mobile device to be added.
     * @return The updated list of mobile devices.
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMobile(Mobile mobile) {
        mobileList.add(mobile);
        return Response.ok(mobile).build();
    }


    /**
     * Updates an existing mobile device in the list.
     * This method handles PUT requests to "/api/v1/mobile/update/{id}" endpoint.
     * It consumes plain text format.
     *
     * @param id       The ID of the mobile device to be updated.
     * @param mobile   The updated mobile device.
     * @return The updated list of mobile devices.
     */
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMobile(@PathParam("id") int id, Mobile mobile) {
        mobileList = mobileList.stream().map(m -> {
            if (m.getId() == id) {
                return mobile;
            } else {
                return m;
            }
        }).collect(Collectors.toList());
        return Response.ok(mobileList).build();
    }


    /**
     * Deletes a mobile device from the list.
     * This method handles DELETE requests to "/api/v1/mobile/delete/{id}" endpoint.
     *
     * @param id The ID of the mobile device to be deleted.
     * @return The updated list of mobile devices.
     */
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMobile(@PathParam("id") int id) {
        mobileList.stream().filter(m -> m.getId() == id).findFirst().ifPresent(mobileList::remove);
        return Response.ok(mobileList).build();
    }
}
