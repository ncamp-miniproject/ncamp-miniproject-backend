package com.model2.mvc.product.controller.editor;

import com.model2.mvc.product.domain.OrderBy;

import java.beans.PropertyEditorSupport;

public class OrderByEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(OrderBy.findByPropertyName(text).orElseThrow(IllegalArgumentException::new));
    }
}
