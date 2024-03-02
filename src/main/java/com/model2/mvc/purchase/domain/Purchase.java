package com.model2.mvc.purchase.domain;

import com.model2.mvc.user.domain.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Purchase {
    private Integer tranNo;
    private User buyer;
    private PaymentOption paymentOption;
    private String receiverName;
    private String receiverPhone;
    private String divyAddr;
    private String divyRequest;
    private TranStatusCode tranStatusCode;
    private Date orderDate;
    private Date divyDate;
    private List<TransactionProduction> transactionProductions;

    public Purchase() {
        this.transactionProductions = new ArrayList<>();
    }

    public Purchase(Integer tranNo) {
        this();
        this.tranNo = tranNo;
    }

    public Integer getTranNo() {
        return tranNo;
    }

    public void setTranNo(Integer tranNo) {
        this.tranNo = tranNo;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
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

    public TranStatusCode getTranStatusCode() {
        return tranStatusCode;
    }

    public void setTranStatusCode(TranStatusCode tranStatusCode) {
        this.tranStatusCode = tranStatusCode;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDivyDate() {
        return divyDate;
    }

    public void setDivyDate(Date divyDate) {
        this.divyDate = divyDate;
    }

    public List<TransactionProduction> getTransactionProductions() {
        return transactionProductions;
    }

    public void setTransactionProductions(List<TransactionProduction> transactionProductions) {
        this.transactionProductions = transactionProductions;
    }

    public void addTransactionProducts(TransactionProduction transactionProduction) {
        this.transactionProductions.add(transactionProduction);
    }

    @Override
    public String toString() {
        return "Purchase{" +
               "tranNo=" +
               tranNo +
               ", buyer=" +
               buyer +
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
               ", tranStatusCode=" +
               tranStatusCode +
               ", orderDate=" +
               orderDate +
               ", divyDate=" +
               divyDate +
               ", transactionProductions=" +
               transactionProductions +
               '}';
    }
}