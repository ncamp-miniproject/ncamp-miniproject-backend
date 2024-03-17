package com.model2.mvc.product.controller.editor;

import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

public class CategoryNoEditor extends PropertyEditorSupport {
    private static final CategoryNoEditor singleton = new CategoryNoEditor();

    private CategoryNoEditor() {
    }

    public static CategoryNoEditor getInstance() {
        return singleton;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.equals("-1")) {
            this.setValue(null);
        } else {
            this.setValue(Integer.parseInt(text));
        }
    }
}
