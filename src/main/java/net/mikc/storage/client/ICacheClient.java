package net.mikc.storage.client;

public interface ICacheClient {
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
    void put(String id, Object o);



    /**
     * Clear the cache.
     */
    void clearAll();
}
