package net.mikc.storage.core;

import com.google.common.eventbus.Subscribe;
import net.mikc.storage.client.CacheInternalApiClient;
import net.mikc.storage.client.ICacheClient;

import java.util.List;

public class EventHandler {
    private final ICacheClient client ;

    public EventHandler(List<String> peers) {
        client = new CacheInternalApiClient(peers);
    }

    @Subscribe
    public void handleCacheEvent(CacheModifiedEvent event) {
        switch (event.eventType()) {
            case PUT -> client.put(event.id(), event.value());
            case REMOVE -> client.delete(event.id());
            case CLEAR_ALL -> client.clearAll();
        }
    }
}
