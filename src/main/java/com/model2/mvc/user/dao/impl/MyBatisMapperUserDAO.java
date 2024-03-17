package com.model2.mvc.user.dao.impl;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.Search;
import com.model2.mvc.user.dao.UserDAO;
import com.model2.mvc.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

@Repository("myBatisMapperUserDAO")
@RequiredArgsConstructor
@Primary
public class MyBatisMapperUserDAO implements UserDAO {
    private final SqlSession sqlSession;

    @Override
    public void insertUser(User user) throws SQLException {
        this.sqlSession.insert("UserMapper.insert", user);
    }

    @Override
    public Optional<User> findByUserId(String userId) throws SQLException {
        return Optional.ofNullable(this.sqlSession.selectOne("UserMapper.findById", userId));
    }

    @Override
    public ListData<User> findByUserName(Search search) throws SQLException {
        search.setSearchKeyword("%" + search.getSearchKeyword() + "%");
        ListData<User> list = this.sqlSession.selectOne("UserMapper.findUsers", search);
        return list == null ? new ListData<>(0, new ArrayList<>()) : list;
    }

    @Override
    public Optional<User> updateUser(User to) throws SQLException {
        User previous = this.sqlSession.selectOne("UserMapper.findById", to.getUserId());
        this.sqlSession.update("UserMapper.update", to);
        return Optional.ofNullable(previous);
    }

    @Override
    public void removeByUserId(String userId) throws SQLException {
        this.sqlSession.delete("UserMapper.delete", userId);
    }
}
