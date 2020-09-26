package net.mikc.storage.core;

public record CacheModifiedEvent (CacheEventType eventType, String id, CacheEntry value) {}
