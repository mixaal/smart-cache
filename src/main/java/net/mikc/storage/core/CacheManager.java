package net.mikc.storage.core;

import com.google.common.eventbus.EventBus;
import net.mikc.storage.discovery.IDiscoveryProvider;

import java.util.List;

public class CacheManager implements ICacheManager {
    final ICacheManager cache;
    final EventBus eventBus = new EventBus("defaultCache");
    public CacheManager(IDiscoveryProvider discoveryProvider, ICacheManager internalCacheManager) {
        this.cache = internalCacheManager;
        eventBus.register(new EventHandler(discoveryProvider));
    }

    @Override
    public void delete(String id) {
        cache.delete(id);
        eventBus.post(new CacheModifiedEvent(CacheEventType.REMOVE, id, null));
    }

    @Override
    public void put(String id, CacheEntry newEntry) {
        cache.put(id, newEntry);
        eventBus.post(new CacheModifiedEvent(CacheEventType.PUT, id, newEntry));
    }

    @Override
    public void clearAll() {
        cache.clearAll();
        eventBus.post(new CacheModifiedEvent(CacheEventType.CLEAR_ALL, null, null));
    }

    @Override
    public CacheEntry get(String id) {
        return cache.get(id);
    }
}
