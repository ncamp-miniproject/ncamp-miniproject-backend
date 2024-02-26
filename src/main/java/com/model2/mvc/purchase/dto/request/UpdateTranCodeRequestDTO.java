package com.model2.mvc.purchase.dto.request;

import com.model2.mvc.purchase.domain.TranStatusCode;

public class UpdateTranCodeRequestDTO {
    private int tranNo;
    private TranStatusCode tranStatusCode;

    public UpdateTranCodeRequestDTO(int tranNo, TranStatusCode tranStatusCode) {
        this.tranNo = tranNo;
        this.tranStatusCode = tranStatusCode;
    }

    public int getTranNo() {
        return tranNo;
    }

    public TranStatusCode getTranStatusCode() {
        return tranStatusCode;
    }

    @Override
    public String toString() {
        return "UpdateTranCodeRequestDTO{" + "tranNo=" + tranNo + ", tranStatusCode=" + tranStatusCode + '}';
    }
}
