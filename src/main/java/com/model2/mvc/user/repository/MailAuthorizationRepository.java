package com.model2.mvc.user.repository;

import com.model2.mvc.user.domain.MailAuthenticationInfo;

import java.util.Optional;

public interface MailAuthorizationRepository {

    public Optional<MailAuthenticationInfo> findByEmail(String email);

    public void save(MailAuthenticationInfo mailAuthenticationInfo);

    public MailAuthenticationInfo updateByEmail(String email, MailAuthenticationInfo newInstance);

    public MailAuthenticationInfo deleteByEmail(String email);
}
