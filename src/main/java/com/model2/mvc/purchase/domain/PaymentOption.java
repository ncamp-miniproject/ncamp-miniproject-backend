package com.model2.mvc.purchase.domain;

import java.util.HashMap;
import java.util.Map;

public enum PaymentOption {
    CASH("0", "현금"),
    CREDIT_CARD("1", "신용카드");

    private static final Map<String, PaymentOption> paymentOptionTable = new HashMap<>();

    static {
        for (PaymentOption paymentOption : PaymentOption.values()) {
            paymentOptionTable.put(paymentOption.code, paymentOption);
        }
    }

    private final String code;
    private final String paymentName;

    PaymentOption(String code, String paymentName) {
        this.code = code;
        this.paymentName = paymentName;
    }

    public static PaymentOption get(String optionCode) {
        return paymentOptionTable.get(optionCode);
    }

    public String getCode() {
        return this.code;
    }

    public String getPaymentName() {
        return this.paymentName;
    }
}
