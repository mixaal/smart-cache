package net.mikc.storage.resources;

import net.mikc.storage.core.CacheEntry;
import net.mikc.storage.core.ICacheManager;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;

@Singleton
@Path("/api/v1")
public class CacheResource {
    private final ICacheManager cacheManager ;
    private final ICacheManager internalCacheManger;

    public CacheResource(
            final ICacheManager cacheManager,
            final ICacheManager internalCacheManger
    ) {
        this.cacheManager = cacheManager;
        this.internalCacheManger = internalCacheManger;
    }

    @DELETE
    @Path("/cache")
    public Response clearAll() {
        cacheManager.clearAll();
        return Response.noContent().build();
    }

    @DELETE
    @Path("/cache/{id}")
    public Response deleteItem(@PathParam("id") String id) {
        cacheManager.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/cache/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CacheEntry getItem(@PathParam("id") String id) {
        return cacheManager.get(id);
    }

    @POST
    @Path("/cache/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putItem(@PathParam("id") String id, CacheEntry entry) {
        Instant now = Instant.now();
        cacheManager.put(id, new CacheEntry(now.toEpochMilli(), id, entry.value()));
        return Response.ok().build();
    }

    @DELETE
    @Path("/internalCache/")
    public Response clearAllInternal() {
        internalCacheManger.clearAll();
        return Response.noContent().build();
    }

    @DELETE
    @Path("/internalCache/{id}")
    public Response deleteItemInternal(@PathParam("id") String id) {
        internalCacheManger.delete(id);
        return Response.noContent().build();
    }

    @POST
    @Path("/internalCache/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putItemInternal(@PathParam("id") String id, CacheEntry entry) {
        internalCacheManger.put(id, new CacheEntry(entry.timeStamp(), id, entry.value()));
        return Response.ok().build();
    }
}
