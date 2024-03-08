package com.develop.resource;

import com.develop.model.Laptop;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Path("/api/v1/laptop")
public class LaptopResource {

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createLaptop(Laptop laptop) {
        Laptop.persist(laptop);
        if (laptop.isPersistent()) {
            return Response.created(URI.create("/laptop/" + laptop.id)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLaptops() {
        List<Laptop> laptopList = Laptop.listAll();
        return Response.ok(laptopList).build();
    }

    @GET
    @Path("/list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLaptopById(@PathParam("id") Long id) {
        Laptop laptop = Laptop.findById(id);
        return Response.ok(laptop).build();
    }

    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateLaptop(@PathParam("id") Long id, Laptop updateLaptop) {
        Optional<Laptop> laptopOptional = Laptop.findByIdOptional(id);
        if (laptopOptional.isPresent()) {
            Laptop currentLaptop = laptopOptional.get();

            if (Objects.nonNull(updateLaptop.getName())) {
                currentLaptop.setName(updateLaptop.getName());
            }

            if (Objects.nonNull(updateLaptop.getBrand())) {
                currentLaptop.setBrand(updateLaptop.getBrand());
            }

            if (Objects.nonNull(updateLaptop.getProcessor())) {
                currentLaptop.setProcessor(updateLaptop.getProcessor());
            }

            if (Objects.nonNull(updateLaptop.getRam())) {
                currentLaptop.setRam(updateLaptop.getRam());
            }

            if (Objects.nonNull(updateLaptop.getStorage())) {
                currentLaptop.setStorage(updateLaptop.getStorage());
            }

            currentLaptop.persist();
            if (currentLaptop.isPersistent()) {
                return Response.created(URI.create("/laptop/" + id)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLaptop(@PathParam("id") Long id) {
        Optional<Laptop> laptopOptional = Laptop.findByIdOptional(id);
        if (laptopOptional.isPresent()) {
            Laptop laptop = laptopOptional.get();
            laptop.delete();
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
