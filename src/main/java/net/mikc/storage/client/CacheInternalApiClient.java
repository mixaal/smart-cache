package net.mikc.storage.client;

import net.mikc.storage.ApplicationFactory;
import net.mikc.storage.discovery.IDiscoveryProvider;
import org.glassfish.jersey.client.ClientProperties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Internal REST API client with Retry policy.
 */
public class CacheInternalApiClient implements ICacheClient {
    private static final String API_PATH = "api";
    private static final String API_VERSION ="v1";
    private static final String CACHE_CTX_PATH="internalCache";
    private final Client client;
    private final IDiscoveryProvider discoveryProvider;


    public CacheInternalApiClient(IDiscoveryProvider discoveryProvider) {
        this.client = ClientBuilder.newClient();
        client.property(ClientProperties.CONNECT_TIMEOUT, 1000);
        client.property(ClientProperties.READ_TIMEOUT,    1000);
        this.discoveryProvider = discoveryProvider;
    }

    @Override
    public void delete(String id) {
        for(String baseUri: discoveryProvider.getPeers()) {
            ApplicationFactory.RestRetryPolicyExecutor.getAsync(() -> {
                Response r = client.target(baseUri).path(API_PATH).path(API_VERSION).path(CACHE_CTX_PATH).path(id).request().delete();
                return r.getStatus() == 204 ? r : null;
            });
        }
    }

    @Override
    public void put(String id, Object o) {
        for(String baseUri: discoveryProvider.getPeers()) {
            ApplicationFactory.RestRetryPolicyExecutor.getAsync(() -> {
                Response r = client.target(baseUri).path(API_PATH).path(API_VERSION).path(CACHE_CTX_PATH).path(id).request().post(
                        Entity.entity(o, MediaType.APPLICATION_JSON_TYPE)
                );
                return r.getStatus() == 200 ? r : null;
            });
        }
    }

    @Override
    public void clearAll() {
        for(String baseUri: discoveryProvider.getPeers()) {
            ApplicationFactory.RestRetryPolicyExecutor.getAsync(() -> {
                Response r = client.target(baseUri).path(API_PATH).path(API_VERSION).path(CACHE_CTX_PATH).request().delete();
                return r.getStatus() == 204 ? r : null;
            });
        }
    }

}
