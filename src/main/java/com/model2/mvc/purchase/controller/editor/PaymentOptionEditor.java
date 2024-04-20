package com.model2.mvc.purchase.controller.editor;

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

    @Override
    public String getAsText() {
        PaymentOption paymentOption = (PaymentOption)super.getValue();
        return paymentOption.getCode();
    }
}
