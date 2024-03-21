package com.model2.mvc.purchase.domain;

import java.util.HashMap;
import java.util.Map;

public enum PaymentOption {
    CASH("0", "현금"),
    CREDIT_CARD("1", "신용카드");

    private static final Map<String, PaymentOption> paymentOptionTable = new HashMap<>();

    static {
        paymentOptionTable.put("0", CASH);
        paymentOptionTable.put("1", CREDIT_CARD);
    }

    private final String code;
    private final String paymentName;

    private PaymentOption(String code, String paymentName) {
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
