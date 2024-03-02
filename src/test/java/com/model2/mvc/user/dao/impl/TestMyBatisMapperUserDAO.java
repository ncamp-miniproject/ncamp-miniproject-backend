package com.model2.mvc.user.dao.impl;

import com.model2.mvc.user.dao.UserDAO;
import com.model2.mvc.user.domain.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config/common.xml", "classpath:spring-config/module/user.xml" })
public class TestMyBatisMapperUserDAO extends TestCase {

    @Autowired
    @Qualifier("myBatisMapperUserDAO")
    private UserDAO userDAO;

    @Test
    public void test() {
        User user = new User("user111111", "username", "1q2w3e4r");
        try {
            userDAO.insertUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}