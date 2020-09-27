package net.mikc.storage.resources;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Singleton
@Path("/api/v1")
public class HealthCheckResource {

    @Path("health")
    @GET
    public Response healthCheck() {
        return Response.ok().build();
    }
}
