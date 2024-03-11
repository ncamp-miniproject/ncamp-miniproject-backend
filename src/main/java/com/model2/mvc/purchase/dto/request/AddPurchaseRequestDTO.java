package com.model2.mvc.purchase.dto.request;

import com.model2.mvc.purchase.domain.PaymentOption;
import com.model2.mvc.purchase.domain.TransactionProduction;

import java.time.LocalDate;
import java.util.List;

public class AddPurchaseRequestDTO {
    private String buyerId;
    private PaymentOption paymentOption;
    private String receiverName;
    private String receiverPhone;
    private String divyAddr;
    private String divyRequest;
    private LocalDate divyDate;
    private List<TransactionProduction> tranProds;

    public AddPurchaseRequestDTO() {
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

    public List<TransactionProduction> getTranProds() {
        return tranProds;
    }

    public void setTranProds(List<TransactionProduction> tranProds) {
        this.tranProds = tranProds;
    }

    @Override
    public String toString() {
        return "AddPurchaseRequestDTO{" +
               "buyerId='" +
               buyerId +
               '\'' +
               ", paymentOption=" +
               paymentOption +
               ", receiverName='" +
               receiverName +
               '\'' +
               ", receiverPhone='" +
               receiverPhone +
               '\'' +
               ", divyAddr='" +
               divyAddr +
               '\'' +
               ", divyRequest='" +
               divyRequest +
               '\'' +
               ", divyDate=" +
               divyDate +
               ", transactionProductions=" + tranProds +
               '}';
    }
}
