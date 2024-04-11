package com.model2.mvc.user.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.MapperWithoutSpringInitializer;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.Setter;
import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class UserMapperTest {
    private static final Logger log = LoggerFactory.getLogger(UserMapperTest.class);
    private SqlSession sqlSession;

    @BeforeEach
    public void init() {
        this.sqlSession = MapperWithoutSpringInitializer.initUnitTest("UserMapper.clear");
    }

    @AfterEach
    public void destroy() {
        MapperWithoutSpringInitializer.afterUnitTest(this.sqlSession, "UserMapper.clear");
    }

    @Test
    public void insertAndFind() {
        String userId = "user1111";
        String userName = "scott";
        String password = "1q2w3e4r";
        Role role = Role.USER;
        String ssn = null;
        String phone = "010-1234-1234";
        String addr = "New York";
        String email = "john@g.com";
        LocalDate regDate = LocalDate.now();

        User userToInsert = new User();
        userToInsert.setUserId(userId);
        userToInsert.setUserName(userName);
        userToInsert.setPassword(password);
        userToInsert.setRole(role);
        userToInsert.setSsn(ssn);
        userToInsert.setPhone(phone);
        userToInsert.setAddr(addr);
        userToInsert.setEmail(email);
        userToInsert.setRegDate(regDate);

        this.sqlSession.insert("UserMapper.insert", userToInsert);

        User queriedUser = sqlSession.selectOne("UserMapper.findById", userId);

        assertThat(queriedUser).isEqualTo(userToInsert);
    }

    static Function<String, User> testCaseGenerate = s -> {
        String[] p = s.split("==");
        User user = new User();
        user.setUserId(p[0]);
        user.setUserName(p[1]);
        user.setPassword(p[2]);
        user.setRole(Role.of(p[3]).get());
        user.setSsn(p[4]);
        user.setPhone(p[5]);
        user.setAddr(p[6]);
        user.setEmail(p[7]);
        user.setRegDate(LocalDate.now());
        log.debug(user.toString());
        return user;
    };

    @Test
    public void findUserListTest() {
        String params = "iuser011==John==1q2w==user==aa==010-1111-1111==asdf==rr@a.kr==13_" +
                        "iuser022==Doe==3e4r==user==bb==111-1111-1111==fff==gg@gmail.com==25_" +
                        "iuser033==zxcv==fefq==user==avs==010-8597-1253==fafe==wga@gmail.com==33";

        List<User> users = Setter.get(params, testCaseGenerate);

        users.forEach(e -> sqlSession.insert("UserMapper.insert", e));

        Map<String, Object> search1 = new HashMap<>();
        search1.put("startRowNum", 1);
        search1.put("endRowNum", 3);
        search1.put("searchCondition", "0");
        search1.put("searchKeyword", "iuser%");
        List<User> found1 = sqlSession.selectList("UserMapper.findUsers", search1);
        int count1 = sqlSession.selectOne("UserMapper.count", search1);

        log.debug(found1.toString());
        checkResult(users, found1, count1);

        Map<String, Object> search2 = new HashMap<>();
        search2.put("startRowNum", 1);
        search2.put("endRowNum", 3);
        search2.put("searchCondition", "1");
        search2.put("searchKeyword", "zxcv");
        List<User> found2 = sqlSession.selectList("UserMapper.findUsers", search2);
        int count2 = sqlSession.selectOne("UserMapper.count", search2);

        assertThat(found2.size()).isEqualTo(1);
        assertThat(found2.get(0)).isEqualTo(users.get(2));
    }

    private static void checkResult(List<User> users, List<User> found, int count) {
        assertThat(count).isEqualTo(3);

        Iterator<User> outputIter = found.iterator();
        Iterator<User> expectedIter = users.iterator();
        while (outputIter.hasNext() || expectedIter.hasNext()) {
            User output = outputIter.next();
            User expected = expectedIter.next();

            assertThat(output.getUserId()).isEqualTo(expected.getUserId());
            assertThat(output.getUserName()).isEqualTo(expected.getUserName());
            assertThat(output.getPassword()).isEqualTo(expected.getPassword());
            assertThat(output.getAddr()).isEqualTo(expected.getAddr());
            assertThat(output.getSsn()).isEqualTo(expected.getSsn());
            assertThat(output.getPhone()).isEqualTo(expected.getPhone());
            assertThat(output.getAddr()).isEqualTo(expected.getAddr());
            assertThat(output.getEmail()).isEqualTo(expected.getEmail());
        }
    }

    @Test
    public void updateUser() {
        String id = "iuser011";
        String param = "iuser011==John==1q2w==user==aa==010-1111-1111==asdf==rr@a.kr==13";
        User user = Setter.get(param, testCaseGenerate).get(0);
        sqlSession.insert("UserMapper.insert", user);

        user.setUserName("Doe");
        sqlSession.update("UserMapper.update", user);
        User found1 = sqlSession.selectOne("UserMapper.findById", id);
        assertThat(found1.getUserName()).isEqualTo("Doe");

        user.setAddr("Seoul");
        sqlSession.update("UserMapper.update", user);
        User found2 = sqlSession.selectOne("UserMapper.findById", id);
        assertThat(found2.getAddr()).isEqualTo("Seoul");
    }
}
