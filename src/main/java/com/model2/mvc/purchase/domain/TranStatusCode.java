package com.model2.mvc.purchase.domain;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum TranStatusCode {
    PURCHASEABLE("판매중", "0"),
    PURCHASE_DONE("구매 완료", "1"),
    IN_DELIVERY("배송중", "2"),
    DELIVERY_DONE("배송 완료", "3");
    
    private String status;
    private String code;
    
    private TranStatusCode(String status, String code) {
        this.status = status;
        this.code = code;
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
    
    private static final EnumSet<TranStatusCode> TRAN_STATUS_CODE_SET = EnumSet.allOf(TranStatusCode.class);
    
    public static String convertCodeToStatus(String code) {
        for (TranStatusCode t : TRAN_STATUS_CODE_SET) {
            if (t.containsCode(code)) {
                return t.status;
            }
        }
        System.out.println("enum TranCode doesn't contains code: " + code);
        return "";
    }
    
    private static final Map<String, TranStatusCode> codeTable = new HashMap<>();
    
    static {
        for (TranStatusCode t : TRAN_STATUS_CODE_SET) {
            codeTable.put(t.getCode(), t);
        }
    }
    
    public static final TranStatusCode getTranCode(String code) {
        if (codeTable.containsKey(code)) {
            return codeTable.get(code);
        }
        throw new IllegalArgumentException("No such code");
    }
}
