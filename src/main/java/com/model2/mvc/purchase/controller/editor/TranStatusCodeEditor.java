package com.model2.mvc.purchase.controller.editor;

import com.model2.mvc.purchase.domain.TranStatusCode;

import java.beans.PropertyEditorSupport;

public class TranStatusCodeEditor extends PropertyEditorSupport {
    private static final TranStatusCodeEditor singleton = new TranStatusCodeEditor();

    private TranStatusCodeEditor() {
    }

    public static TranStatusCodeEditor getInstance() {
        return singleton;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        super.setValue(TranStatusCode.findTranCode(text));
    }
}
