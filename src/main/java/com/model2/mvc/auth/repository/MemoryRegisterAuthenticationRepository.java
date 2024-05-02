package com.model2.mvc.auth.repository;

import com.model2.mvc.user.domain.MailAuthenticationInfo;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Deprecated
public class MemoryRegisterAuthenticationRepository implements RegisterAuthenticationRepository {
    private static final Map<String, MailAuthenticationInfo> storage = new ConcurrentHashMap<>();

    @Override
    public Optional<MailAuthenticationInfo> findByEmail(String email) {
        return Optional.ofNullable(storage.get(email));
    }

    @Override
    public void save(MailAuthenticationInfo mailAuthenticationInfo) {
        storage.put(mailAuthenticationInfo.getEmail(), mailAuthenticationInfo);
    }

    @Override
    public MailAuthenticationInfo updateByEmail(String email, MailAuthenticationInfo newInstance) {
        return storage.put(email, newInstance);
    }

    @Override
    public MailAuthenticationInfo deleteByEmail(String email) {
        return storage.remove(email);
    }
}
