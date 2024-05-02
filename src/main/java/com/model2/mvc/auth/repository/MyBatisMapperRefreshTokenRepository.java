package com.model2.mvc.auth.repository;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;

@Repository
@Primary
@RequiredArgsConstructor
public class MyBatisMapperRefreshTokenRepository implements RefreshTokenRepository {
    private final SqlSession sqlSession;

    @Override
    public boolean save(String token) {
        if (this.retrieve(token)) {
            return false;
        }
        this.sqlSession.insert("MetadataMapper.saveRefreshToken", token);
        return true;
    }

    @Override
    public boolean retrieve(String token) {
        String found = this.sqlSession.selectOne("MetadataMapper.findRefreshToken", token);
        return found != null;
    }

    @Override
    public boolean remove(String token) {
        if (!this.retrieve(token)) {
            return false;
        }
        this.sqlSession.delete("MetadataMapper.removeRefreshToken", token);
        return true;
    }

    @PreDestroy
    public void clear() {
        this.sqlSession.delete("MetadataMapper.clearRefreshToken");
    }
}
