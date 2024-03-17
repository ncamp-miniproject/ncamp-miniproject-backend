package com.model2.mvc.user.domain;

public enum Role {
    USER("user"),
    SELLER("seller"),
    ADMIN("admin");

    private String value;

    private Role(String value) {
        this.value = value;
    }

    public String role() {
        return this.value;
    }
}
