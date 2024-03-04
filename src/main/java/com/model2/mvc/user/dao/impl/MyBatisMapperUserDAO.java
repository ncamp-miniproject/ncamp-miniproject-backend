package com.model2.mvc.user.dao.impl;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.dto.Search;
import com.model2.mvc.user.dao.UserDAO;
import com.model2.mvc.user.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository("myBatisMapperUserDAO")
@Primary
public class MyBatisMapperUserDAO implements UserDAO {
    private SqlSession sqlSession;

    @Autowired
    public MyBatisMapperUserDAO(@Qualifier("sqlSessionTemplate") SqlSession sqlSession) {
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
        search.setSearchKeyword("%" + search.getSearchKeyword() + "%");
        return this.sqlSession.selectOne("UserMapper.findUsers", search);
    }

    @Override
    public User updateUser(User to) throws SQLException {
        User previous = this.sqlSession.selectOne("UserMapper.findById", to.getUserId());
        this.sqlSession.update("UserMapper.update", to);
        return previous;
    }
}
