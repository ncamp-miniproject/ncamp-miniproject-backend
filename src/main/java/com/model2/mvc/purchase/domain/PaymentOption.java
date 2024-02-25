package com.model2.mvc.purchase.domain;

import java.util.HashMap;
import java.util.Map;

public enum PaymentOption {
    CASH("0"),
    CREDIT_CARD("1");

    private static final Map<String, PaymentOption> paymentOptionTable = new HashMap<>();

    static {
        paymentOptionTable.put("0", CASH);
        paymentOptionTable.put("1", CREDIT_CARD);
    }

    private String paymentOption;

    private PaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public static PaymentOption get(String optionCode) {
        return paymentOptionTable.get(optionCode);
    }

    public String paymentOption() {
        return this.paymentOption;
    }
}
