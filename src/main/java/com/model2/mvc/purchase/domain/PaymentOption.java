package com.model2.mvc.purchase.domain;

public enum PaymentOption {
    CASH("0"),
    CREDIT_CARD("1");

    private String paymentOption;
    
    private PaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }
    
    public String paymentOption() {
        return this.paymentOption;
    }
}
