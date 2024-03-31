package com.model2.mvc.user.repository;

import com.model2.mvc.user.domain.MailAuthorizationInfo;

import java.util.Optional;

public interface MailAuthorizationRepository {

    public Optional<MailAuthorizationInfo> findByEmail(String email);

    public void save(MailAuthorizationInfo mailAuthorizationInfo);

    public MailAuthorizationInfo updateByEmail(String email, MailAuthorizationInfo newInstance);

    public MailAuthorizationInfo deleteByEmail(String email);
}
