package com.model2.mvc.user.repository.impl;

import com.model2.mvc.user.domain.MailAuthorizationInfo;
import com.model2.mvc.user.repository.MailAuthorizationRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryMailAuthorizationRepository implements MailAuthorizationRepository {
    private static final Map<String, MailAuthorizationInfo> storage = new ConcurrentHashMap<>();

    @Override
    public Optional<MailAuthorizationInfo> findByEmail(String email) {
        return Optional.ofNullable(storage.get(email));
    }

    @Override
    public void save(MailAuthorizationInfo mailAuthorizationInfo) {
        storage.put(mailAuthorizationInfo.getEmail(), mailAuthorizationInfo);
    }

    @Override
    public MailAuthorizationInfo updateByEmail(String email, MailAuthorizationInfo newInstance) {
        return storage.put(email, newInstance);
    }

    @Override
    public MailAuthorizationInfo deleteByEmail(String email) {
        return storage.remove(email);
    }
}
