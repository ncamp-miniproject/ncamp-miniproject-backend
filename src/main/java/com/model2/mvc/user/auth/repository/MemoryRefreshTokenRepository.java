package com.model2.mvc.user.auth.repository;

import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryRefreshTokenRepository implements RefreshTokenRepository {
    private static final Set<String> store = ConcurrentHashMap.newKeySet();

    @Override
    public boolean save(String token) {
        if (store.contains(token)) {
            return false;
        }
        store.add(token);
        return true;
    }

    @Override
    public boolean retrieve(String token) {
        return store.contains(token);
    }

    @Override
    public boolean remove(String token) {
        return store.remove(token);
    }
}
