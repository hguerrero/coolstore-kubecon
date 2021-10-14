package com.redhat.cloudnative;

import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.WebApplicationException;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("api/products")
public class CatalogResource {

    @Inject
    CatalogService catalogService;

    @GET
    public List<Product> getAll() {
        return catalogService.getAll();
    }

    @GET
    @Path("{id}")
    public Product getProductById(@PathParam String id) {
        return catalogService.getProductById(id);
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Override
        public Response toResponse(Exception exception) {
            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }
            return Response.status(code)
                    .entity(Json.createObjectBuilder().add("error", exception.getMessage()).add("code", code).build())
                    .build();
        }

    }

}
