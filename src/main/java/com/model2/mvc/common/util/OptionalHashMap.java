package com.model2.mvc.common.util;

import java.util.HashMap;
import java.util.Optional;

public class OptionalHashMap<K, V> extends HashMap<K, V> {

    public Optional<V> getOptional(K key) {
        return Optional.ofNullable(super.get(key));
    }
}
