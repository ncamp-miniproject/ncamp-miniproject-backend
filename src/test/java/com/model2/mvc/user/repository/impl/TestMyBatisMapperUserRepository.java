package com.model2.mvc.user.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.fail;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.Search;
import com.model2.mvc.config.context.ContextConfig;
import com.model2.mvc.config.context.MyBatisConfig;
import com.model2.mvc.config.context.PropertyConfig;
import com.model2.mvc.config.context.TransactionConfig;
import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@SpringJUnitConfig(classes = {
        ContextConfig.class, MyBatisConfig.class, PropertyConfig.class, TransactionConfig.class
})
public class TestMyBatisMapperUserRepository {

    @Autowired
    @Qualifier("myBatisMapperUserDAO")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("sqlSessionTemplate")
    private SqlSession sqlSession;

    @BeforeEach
    public void beforeTest() {
        this.sqlSession.delete("UserMapper.clear");
    }

    @AfterEach
    public void afterTest() {
        this.sqlSession.delete("UserMapper.clear");
    }

    @Test
    public void test() {
        User user = new User("user111111", "username", "1q2w3e4r");
        try {
            userRepository.insertUser(user);
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
        user.setRole(Role.ADMIN);
        user.setSsn("fff");
        user.setPhone("010-1234-1234");
        user.setAddr("Daegu");
        user.setEmail("a@gmail.com");
        user.setRegDate(LocalDate.of(2017, 1, 13));
        try {
            this.userRepository.insertUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }

        try {
            Optional<User> found = this.userRepository.findByUserId("sampleuser");
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
        user.setRole(Role.ADMIN);
        user.setSsn("fff");
        user.setPhone("010-1234-1234");
        user.setAddr("Daegu");
        user.setEmail("a@gmail.com");
        user.setRegDate(LocalDate.now());

        assertThatExceptionOfType(Exception.class).isThrownBy(() -> this.userRepository.insertUser(user));
    }

    @Test
    public void insertUser_duplicateOfUserId() {
        User user1 = new User("user1");
        user1.setUserName("username1");
        user1.setPassword("1q2w3e4r");
        user1.setRegDate(LocalDate.now());

        User user2 = new User("user1");
        user2.setUserName("username2");
        user2.setPassword("2w3e4r5t");
        user2.setRegDate(LocalDate.now());

        try {
            this.userRepository.insertUser(user1);
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }

        assertThatExceptionOfType(DuplicateKeyException.class).isThrownBy(() -> this.userRepository.insertUser(user2));
    }

    @Test
    public void findByUserName() {
        User user1 = new User("user1");
        user1.setUserName("username1");
        user1.setPassword("1q2w3e4r");
        user1.setRegDate(LocalDate.now());
        try {
            this.userRepository.insertUser(user1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        User user2 = new User("user2");
        user2.setUserName("username2");
        user2.setPassword("2w3e4r5t");
        user2.setRegDate(LocalDate.now());
        try {
            this.userRepository.insertUser(user2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        Search search = new Search();
        search.setSearchKeyword("user");
        search.setSearchCondition("1");
        search.setStartRowNum(1);
        search.setEndRowNum(3);

        try {
            ListData<User> result = this.userRepository.findByUserName(search);

            assertThat(result.getCount()).isEqualTo(2);
            assertThat(result.getList().get(0)).isEqualTo(user1);
            assertThat(result.getList().get(1)).isEqualTo(user2);
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }
}