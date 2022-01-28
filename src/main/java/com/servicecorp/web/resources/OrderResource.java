package com.servicecorp.web.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

// Required Javax Core Imports
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

// Required Local Imports
import com.servicecorp.web.model.Order;
import com.servicecorp.web.services.OrderService;

import java.util.List;

import javax.validation.Valid;


@Path("/orders")
public class OrderResource {
    private OrderService orderService = new OrderService();

    // Get list of all orders
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> fetchAll() {
        return orderService.fetchAll();
    }

    // Fetch order by ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) throws NotFoundException {
        Order order = orderService.fetchById(id);
        return Response.ok().entity(order).build();
    }

    // Create a new order
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid Order order) {
        Order newOrder = orderService.create(order);
        return Response.status(Status.CREATED).entity(newOrder).build();
    }

    // Update an existing order
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Order order) throws NotFoundException {
        orderService.update(id, order);
        return Response.noContent().build();
    }

    // Delete an order
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) throws NotFoundException {
        orderService.delete(id);
        return Response.status(202).entity("User deleted successfully").build();
    }
}