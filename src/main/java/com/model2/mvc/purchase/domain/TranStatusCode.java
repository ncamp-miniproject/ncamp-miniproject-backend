package com.model2.mvc.purchase.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum TranStatusCode {
    PURCHASEABLE("판매중", "0"),
    PURCHASE_DONE("구매 완료", "1"),
    IN_DELIVERY("배송중", "2"),
    DELIVERY_DONE("배송 완료", "3");

    private static final Map<String, TranStatusCode> CODE_TABLE;

    static {
        TranStatusCode[] tranStatusCodes = TranStatusCode.values();
        Map<String, TranStatusCode> codeTable = new HashMap<>();
        for (TranStatusCode t : tranStatusCodes) {
            codeTable.put(t.getCode(), t);
        }
        CODE_TABLE = Collections.unmodifiableMap(codeTable);
    }

    private final String status;
    private final String code;

    private TranStatusCode(String status, String code) {
        this.status = status;
        this.code = code;
    }

    public static TranStatusCode findTranCode(String code) {
        code = code.trim();
        if (CODE_TABLE.containsKey(code)) {
            return CODE_TABLE.get(code);
        }
        throw new IllegalArgumentException("No such code: " + code);
    }

    public String getStatus() {
        return this.status;
    }

    public String getCode() {
        return this.code;
    }

    private boolean containsCode(String code) {
        return status.equals(this.code);
    }

    @Override
    public String toString() {
        return this.code;
    }
}
