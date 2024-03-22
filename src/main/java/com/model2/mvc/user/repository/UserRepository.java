package com.model2.mvc.user.repository;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.Search;
import com.model2.mvc.user.domain.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserRepository {

    public void insertUser(User user) throws SQLException;

    public Optional<User> findByUserId(String userId) throws SQLException;

    public ListData<User> findByUserName(Search search) throws SQLException;

    public Optional<User> updateUser(User to) throws SQLException;

    public void removeByUserId(String userId) throws SQLException;
}
