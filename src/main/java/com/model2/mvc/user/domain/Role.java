package com.model2.mvc.user.domain;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Role {
    USER,
    SELLER,
    ADMIN;

    private static final Map<String, Role> roleMap = new HashMap<>();

    static {
        EnumSet<Role> roles = EnumSet.allOf(Role.class);
        for (Role role : roles) {
            roleMap.put(role.name(), role);
        }
    }

    public static Optional<Role> of(String value) {
        return Optional.ofNullable(roleMap.get(value.trim().toUpperCase()));
    }
}
