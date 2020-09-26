package net.mikc.storage.core;

/**
 * Cache manager.
 */
public interface ICacheManager {
    /**
     * Delete entry by id
     * @param id id to to remove
     */
    void delete(String id);

    /**
     * Put object to cache.
     * @param id object id
     * @param o object to put
     */
    void put(String id, CacheEntry o);

    /**
     * Get object by id.
     *
     * @param id object id
     * @return object to retrieved
     */
    CacheEntry get(String id);

    /**
     * Clear the cache.
     */
    void clearAll();
}
