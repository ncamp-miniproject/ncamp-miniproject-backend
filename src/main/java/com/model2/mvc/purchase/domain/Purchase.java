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

    public Purchase(Integer tranNo,
                    User buyer,
                    PaymentOption paymentOption,
                    String receiverName,
                    String receiverPhone,
                    String divyAddr,
                    String divyRequest,
                    TranStatusCode tranStatusCode,
                    Date orderDate,
                    Date divyDate,
                    List<TransactionProduction> transactionProductions) {
        this.tranNo = tranNo;
        this.buyer = buyer;
        this.paymentOption = paymentOption;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.divyAddr = divyAddr;
        this.divyRequest = divyRequest;
        this.tranStatusCode = tranStatusCode;
        this.orderDate = orderDate;
        this.divyDate = divyDate;
        this.transactionProductions = transactionProductions;
    }

    public Integer getTranNo() {
        return tranNo;
    }

    public User getBuyer() {
        return buyer;
    }

    public PaymentOption getPaymentOption() {
        return paymentOption;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public String getDivyAddr() {
        return divyAddr;
    }

    public String getDivyRequest() {
        return divyRequest;
    }

    public TranStatusCode getTranStatusCode() {
        return tranStatusCode;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getDivyDate() {
        return divyDate;
    }

    public List<TransactionProduction> getTransactionProductions() {
        return transactionProductions;
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