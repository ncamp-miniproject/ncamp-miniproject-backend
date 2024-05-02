package com.model2.mvc.auth.repository;

import com.model2.mvc.user.domain.MailAuthenticationInfo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class MyMatisMapperRegisterAuthenticationRepository implements RegisterAuthenticationRepository {
    private final SqlSession sqlSession;

    @Override
    public Optional<MailAuthenticationInfo> findByEmail(String email) {
        return Optional.ofNullable(this.sqlSession.selectOne("MetadataMapper.findMailAuthInfo", email));
    }

    @Override
    public void save(MailAuthenticationInfo mailAuthenticationInfo) {
        this.sqlSession.insert("MetadataMapper.saveMailAuthInfo", mailAuthenticationInfo);
    }

    @Override
    public MailAuthenticationInfo updateByEmail(String email, MailAuthenticationInfo newInstance) {
        Optional<MailAuthenticationInfo> before = this.findByEmail(email);
        this.sqlSession.update("MetadataMapper.updateMailAuthInfo", Map.of(
                "email", email,
                "auth", newInstance
        ));
        return before.orElse(null);
    }

    @Override
    public MailAuthenticationInfo deleteByEmail(String email) {
        Optional<MailAuthenticationInfo> before = this.findByEmail(email);
        this.sqlSession.update("MetadataMapper.deleteMailAuthInfo", email);
        return before.orElse(null);
    }

    @PreDestroy
    public void clear() {
        this.sqlSession.delete("MetadataMapper.clearMailAuthInfo");
    }
}
