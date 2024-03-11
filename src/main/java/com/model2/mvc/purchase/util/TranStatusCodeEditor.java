package com.model2.mvc.purchase.util;

import com.model2.mvc.purchase.domain.TranStatusCode;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class TranStatusCodeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        super.setValue(TranStatusCode.getTranCode(text));
    }
}
