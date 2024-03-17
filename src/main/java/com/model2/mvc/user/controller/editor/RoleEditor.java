package com.model2.mvc.user.controller.editor;

import com.model2.mvc.user.domain.Role;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

public class RoleEditor extends PropertyEditorSupport {
    private static final RoleEditor singleton = new RoleEditor();

    public static RoleEditor getInstance() {
        return singleton;
    }

    @Override
    public String getAsText() {
        Role value = (Role)super.getValue();
        return value.getRole();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Optional<Role> role = Role.of(text);
        super.setValue(role.orElseThrow(RuntimeException::new));
    }
}
