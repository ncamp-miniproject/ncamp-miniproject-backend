package com.model2.mvc.user.domain;

public enum Role {
    USER("user"),
    ADMIN("admin");
    
    private String role;
    
    private Role(String role) {
        this.role = role;
    }
    
    public String role() {
        return this.role;
    }
}
