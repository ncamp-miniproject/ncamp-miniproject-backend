package com.model2.mvc.product.controller.editor;

import com.model2.mvc.product.domain.OrderBy;

import java.beans.PropertyEditorSupport;

public class OrderByEditor extends PropertyEditorSupport {
    private static final OrderByEditor singleton = new OrderByEditor();

    private OrderByEditor() {
    }

    public static OrderByEditor getInstance() {
        return singleton;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        super.setValue(OrderBy.findByPropertyName(text)
                               .orElseThrow(() -> new IllegalArgumentException("No OrderBy instance matching: " +
                                                                               text)));
    }

    @Override
    public String getAsText() {
        return ((OrderBy)super.getValue()).getPropertyName();
    }
}
