package com.model2.mvc.user.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.Search;
import com.model2.mvc.user.dao.UserDAO;
import com.model2.mvc.user.domain.User;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-config/context-*.xml" })
public class TestMyBatisMapperUserDAO extends TestCase {

    @Autowired
    @Qualifier("myBatisMapperUserDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("sqlSessionTemplate")
    private SqlSession sqlSession;

    @Before
    public void beforeTest() {
        this.sqlSession.delete("UserMapper.clear");
    }

    @After
    public void afterTest() {
        this.sqlSession.delete("UserMapper.clear");
    }

    @Test
    public void test() {
        User user = new User("user111111", "username", "1q2w3e4r");
        try {
            userDAO.insertUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setUserId("sampleuser");
        user.setUserName("username1");
        user.setPassword("1q2w3e4r");
        user.setRole("admin");
        user.setSsn("fff");
        user.setPhone("010-1234-1234");
        user.setAddr("Daegu");
        user.setEmail("a@gmail.com");
        user.setRegDate(new Date(2017, 3, 24));
        try {
            this.userDAO.insertUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }

        try {
            Optional<User> found = this.userDAO.findByUserId("sampleuser");
            assertThat(found.orElseThrow(Exception::new)).isEqualTo(user);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void insertUser_withoutId() {
        User user = new User();
        user.setUserName("username1");
        user.setPassword("1q2w3e4r");
        user.setRole("admin");
        user.setSsn("fff");
        user.setPhone("010-1234-1234");
        user.setAddr("Daegu");
        user.setEmail("a@gmail.com");
        user.setRegDate(new Date(System.currentTimeMillis()));

        assertThatExceptionOfType(MyBatisSystemException.class).isThrownBy(() -> this.userDAO.insertUser(user));
    }

    @Test
    public void insertUser_duplicateOfUserId() {
        User user1 = new User("user1");
        user1.setUserName("username1");
        user1.setPassword("1q2w3e4r");
        user1.setRegDate(new Date(System.currentTimeMillis()));

        User user2 = new User("user1");
        user2.setUserName("username2");
        user2.setPassword("2w3e4r5t");
        user2.setRegDate(new Date(System.currentTimeMillis()));

        try {
            this.userDAO.insertUser(user1);
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }

        assertThatExceptionOfType(DuplicateKeyException.class).isThrownBy(() -> this.userDAO.insertUser(user2));
    }

    @Test
    public void findByUserName() {
        User user1 = new User("user1");
        user1.setUserName("username1");
        user1.setPassword("1q2w3e4r");
        user1.setRegDate(new Date(System.currentTimeMillis()));
        try {
            this.userDAO.insertUser(user1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        User user2 = new User("user2");
        user2.setUserName("username2");
        user2.setPassword("2w3e4r5t");
        user2.setRegDate(new Date(System.currentTimeMillis()));
        try {
            this.userDAO.insertUser(user2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        Search search = new Search();
        search.setSearchKeyword("user");
        search.setSearchCondition("1");
        search.setStartRowNum(1);
        search.setEndRowNum(3);

        try {
            ListData<User> result = this.userDAO.findByUserName(search);

            assertThat(result.getCount()).isEqualTo(2);
            assertThat(result.getList().get(0)).isEqualTo(user1);
            assertThat(result.getList().get(1)).isEqualTo(user2);
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }
}