package com.model2.mvc.user.mapper;

import static org.assertj.core.api.Assertions.*;

import com.model2.mvc.user.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Date;

public class UserMapperTest {
    private SqlSession sqlSession;

    @Before
    public void init() {
        try (Reader reader = Resources.getResourceAsReader("mybatis-config/mybatis-config-test.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            this.sqlSession = sqlSessionFactory.openSession(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @After
    public void destroy() {
        this.sqlSession.delete("UserMapper.clear");
        this.sqlSession.close();
    }

    @Test
    public void insertAndFind() {
        String userId = "user1111";
        String userName = "scott";
        String password = "1q2w3e4r";
        String role = "user";
        String ssn = null;
        String phone = "010-1234-1234";
        String addr = "New York";
        String email = "john@g.com";
        Date regDate = new Date(System.currentTimeMillis());

        User userToInsert = new User(userId, userName, password, role, ssn, phone, addr, email, regDate);

        this.sqlSession.insert("UserMapper.insertUser", userToInsert);

        User queriedUser = sqlSession.selectOne("UserMapper.findById", userId);

        assertThat(queriedUser).isEqualTo(userToInsert);
    }
}
