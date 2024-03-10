package com.model2.mvc.purchase.dto.request;

import com.model2.mvc.common.Buildable;
import com.model2.mvc.common.BuilderTemplate;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.purchase.domain.PaymentOption;

import java.time.LocalDate;

public class UpdatePurchaseRequestDTO {
    private int tranNo;
    private String buyerId;
    private PaymentOption paymentOption;
    private String receiverName;
    private String receiverPhone;
    private String divyAddr;
    private String divyRequest;
    private LocalDate divyDate;

    public UpdatePurchaseRequestDTO() {
    }

    public int getTranNo() {
        return tranNo;
    }

    public void setTranNo(int tranNo) {
        this.tranNo = tranNo;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public PaymentOption getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(PaymentOption paymentOption) {
        this.paymentOption = paymentOption;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getDivyAddr() {
        return divyAddr;
    }

    public void setDivyAddr(String divyAddr) {
        this.divyAddr = divyAddr;
    }

    public String getDivyRequest() {
        return divyRequest;
    }

    public void setDivyRequest(String divyRequest) {
        this.divyRequest = divyRequest;
    }

    public LocalDate getDivyDate() {
        return divyDate;
    }

    public void setDivyDate(LocalDate divyDate) {
        this.divyDate = divyDate;
    }
}
