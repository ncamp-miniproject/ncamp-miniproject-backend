package com.model2.mvc.purchase.util;

import com.model2.mvc.purchase.domain.PaymentOption;

import java.beans.PropertyEditorSupport;

public class PaymentOptionEditor extends PropertyEditorSupport {
    private static final PaymentOptionEditor singleton = new PaymentOptionEditor();

    private PaymentOptionEditor() {
    }

    public static PaymentOptionEditor getInstance() {
        return singleton;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        super.setValue(PaymentOption.get(text));
    }
}
