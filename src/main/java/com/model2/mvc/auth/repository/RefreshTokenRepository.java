package com.model2.mvc.auth.repository;

public interface RefreshTokenRepository {

    public boolean save(String token);

    public boolean retrieve(String token);

    public boolean remove(String token);
}
