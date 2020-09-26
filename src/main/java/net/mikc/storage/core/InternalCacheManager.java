package net.mikc.storage.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InternalCacheManager implements  ICacheManager {
    final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();

    @Override
    public void delete(String id) {
        cache.remove(id);
    }

    @Override
    public void put(String id, CacheEntry newEntry) {
        CacheEntry oldEntry = cache.putIfAbsent(id, newEntry);
        if(oldEntry==null) return;
        if(oldEntry.timeStamp() < newEntry.timeStamp()) {
            cache.put(id, newEntry);
        }
//        CacheEntry oldEntry;
//        do {
//            oldEntry = cache.putIfAbsent(newEntry.id(), newEntry);
//        } while (oldEntry != null && oldEntry.before(newEntry) && !cache.replace(newEntry.id(), oldEntry, newEntry));
    }

    @Override
    public CacheEntry get(String id) {
        return cache.get(id);
    }

    @Override
    public void clearAll() {
        cache.clear();
    }
}
