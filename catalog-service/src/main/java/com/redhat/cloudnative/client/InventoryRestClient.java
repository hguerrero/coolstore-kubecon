package com.redhat.cloudnative.client;

import com.redhat.cloudnative.Inventory;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/api")
@RegisterRestClient 
public interface InventoryRestClient {

    @GET
    @Path("/inventory/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Inventory> getAvailability(@PathParam String itemId);
}

