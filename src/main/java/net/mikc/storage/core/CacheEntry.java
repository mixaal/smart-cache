package net.mikc.storage.core;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Objects;

public record CacheEntry(@JsonSerialize Long timeStamp, @JsonSerialize String id, @JsonSerialize Object value) {
    boolean before(CacheEntry anotherOne) {
        if(anotherOne==null) return false;
        return timeStamp < anotherOne.timeStamp ;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null) return false;
        if( obj instanceof  CacheEntry ) {
            CacheEntry another = (CacheEntry)obj;
            return another.id().equals(id) && another.value().equals(value) && another.timeStamp() == timeStamp;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, timeStamp);
    }
}
