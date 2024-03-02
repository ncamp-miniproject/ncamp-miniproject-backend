package com.model2.mvc.user.dao.impl;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.dto.Search;
import com.model2.mvc.user.dao.UserDAO;
import com.model2.mvc.user.domain.User;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class MyBatisMapperUserDAO implements UserDAO {
    private SqlSession sqlSession;

    public MyBatisMapperUserDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void insertUser(User user) throws SQLException {
        this.sqlSession.insert("UserMapper.insert", user);
    }

    @Override
    public User findByUserId(String userId) throws SQLException {
        return this.sqlSession.selectOne("UserMapper.findById", userId);
    }

    @Override
    public ListData<User> findByUserName(Search search) throws SQLException {
        return this.sqlSession.selectOne("UserMapper.findUsers", search);
    }

    @Override
    public User updateUser(User to) throws SQLException {
        User previous = this.sqlSession.selectOne("UserMapper.findById", to.getUserId());
        this.sqlSession.update("UserMapper.update", to);
        return previous;
    }
}
