package com.model2.mvc.user.repository.impl;

import com.model2.mvc.common.SearchCondition;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("myBatisMapperUserDAO")
@RequiredArgsConstructor
@Primary
public class MyBatisMapperUserRepository implements UserRepository {
    private final SqlSession sqlSession;

    @Override
    public boolean insertUser(User user) {
        try {
            this.sqlSession.insert("UserMapper.insert", user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return Optional.ofNullable(this.sqlSession.selectOne("UserMapper.findById", userId));
    }

    @Override
    public List<User> findByUserName(String userName, boolean match, int page, int pageSize) {
        Map<String, Object> searchOptions = generateSearchOption(userName, SearchCondition.BY_NAME, match, page, pageSize);
        List<User> result = this.sqlSession.selectList("UserMapper.findUsers", searchOptions);
        return result == null ? new ArrayList<>() : result;
    }

    @Override
    public int countByUserName(String userName, boolean match) {
        Map<String, Object> searchOptions = generateSearchOption(userName, SearchCondition.BY_NAME, match);
        return this.sqlSession.selectOne("UserMapper.count", searchOptions);
    }

    private Map<String, Object> generateSearchOption(String searchKeyword, SearchCondition searchCondition, boolean match) {
        Map<String, Object> search = new HashMap<>();
        search.put("searchKeyword", match ? searchKeyword : "%" + searchKeyword + "%");
        search.put("searchCondition", searchCondition.getConditionCode());
        return search;
    }

    private Map<String, Object> generateSearchOption(String userName, SearchCondition searchCondition, boolean match, int page, int pageSize) {
        Map<String, Object> search = generateSearchOption(userName, searchCondition, match);
        search.put("startRowNum", (page - 1) * pageSize + 1);
        search.put("endRowNum", page * pageSize);
        return search;
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
