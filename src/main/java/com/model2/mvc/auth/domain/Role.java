package com.model2.mvc.auth.domain;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Role {
    USER("user"),
    SELLER("seller"),
    ADMIN("admin");

    private String value;

    private Role(String value) {
        this.value = value;
    }

    public String getRole() {
        return this.value;
    }

    private static final Map<String, Role> roleMap = new HashMap<>();

    static {
        EnumSet<Role> roles = EnumSet.allOf(Role.class);
        for (Role role : roles) {
            roleMap.put(role.getRole(), role);
        }
    }

    public static Optional<Role> of(String value) {
        return Optional.ofNullable(roleMap.get(value.trim().toLowerCase()));
    }
}
