package com.model2.mvc.purchase.util;

import com.model2.mvc.purchase.domain.PaymentOption;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class PaymentOptionEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        super.setValue(PaymentOption.get(text));
    }
}
