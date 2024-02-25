package com.model2.mvc.purchase.domain;

import com.model2.mvc.common.Buildable;
import com.model2.mvc.common.BuilderTemplate;
import com.model2.mvc.user.domain.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Purchase implements Buildable<Purchase.Builder> {
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

    private Purchase(Builder builder) {
        this.tranNo = builder.tranNo;
        this.buyer = builder.buyer;
        this.paymentOption = builder.paymentOption;
        this.receiverName = builder.receiverName;
        this.receiverPhone = builder.receiverPhone;
        this.divyAddr = builder.divyAddr;
        this.divyRequest = builder.divyRequest;
        this.tranStatusCode = builder.tranStatusCode;
        this.orderDate = builder.orderDate;
        this.divyDate = builder.divyDate;
        this.transactionProductions = builder.transactionProductions;
    }

    public Builder builder() {
        return new Builder(this);
    }

    public User getBuyer() {
        return buyer;
    }

    public String getDivyAddr() {
        return divyAddr;
    }

    public Date getDivyDate() {
        return divyDate;
    }

    public String getDivyRequest() {
        return divyRequest;
    }

    public Date getOrderDate() {
        return orderDate;
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

    public TranStatusCode getTranStatusCode() {
        return tranStatusCode;
    }

    public Integer getTranNo() {
        return tranNo;
    }

    public String getTranStatus() {
        return this.tranStatusCode.getStatus();
    }

    public List<TransactionProduction> getTransactionProductions() {
        return Collections.unmodifiableList(this.transactionProductions);
    }

    public void addTransactionProduction(TransactionProduction transactionProductions) {
        this.transactionProductions.add(transactionProductions);
    }

    @Override
    public String toString() {
        return "Purchase{" +
               "tranNo=" +
               tranNo +
               ", buyer=" +
               buyer +
               ", paymentOption='" +
               paymentOption +
               '\'' +
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
               ", divyDate='" +
               divyDate +
               '\'' +
               ", transactionProductions=" +
               transactionProductions +
               '}';
    }

    public static class Builder extends BuilderTemplate<Purchase> {
        private Integer tranNo = null;
        private User buyer = null;
        private PaymentOption paymentOption = null;
        private String receiverName = null;
        private String receiverPhone = null;
        private String divyAddr = null;
        private String divyRequest = null;
        private TranStatusCode tranStatusCode = null;
        private Date orderDate = null;
        private Date divyDate = null;
        private List<TransactionProduction> transactionProductions;

        public Builder(Purchase purchase) {
            this.tranNo = purchase.tranNo;
            this.buyer = purchase.buyer;
            this.paymentOption = purchase.paymentOption;
            this.receiverName = purchase.receiverName;
            this.receiverPhone = purchase.receiverPhone;
            this.divyAddr = purchase.divyAddr;
            this.divyRequest = purchase.divyRequest;
            this.tranStatusCode = purchase.tranStatusCode;
            this.orderDate = purchase.orderDate;
            this.divyDate = purchase.divyDate;
            this.transactionProductions = purchase.transactionProductions;
        }

        public Purchase build() {
            return new Purchase(this);
        }

        public Builder tranNo(Integer no) {
            return this.setField(b -> b.tranNo = no);
        }

        public Builder buyer(User bu) {
            return this.setField(b -> b.buyer = bu);
        }

        public Builder paymentOption(PaymentOption po) {
            return this.setField(b -> b.paymentOption = po);
        }

        public Builder receiverName(String rn) {
            return this.setField(b -> b.receiverName = rn);
        }

        public Builder receiverPhone(String rp) {
            return this.setField(b -> b.receiverPhone = rp);
        }

        public Builder divyAddr(String addr) {
            return this.setField(b -> b.divyAddr = addr);
        }

        public Builder divyRequest(String req) {
            return this.setField(b -> b.divyRequest = req);
        }

        public Builder tranStatusCode(TranStatusCode code) {
            return this.setField(b -> b.tranStatusCode = code);
        }

        public Builder orderDate(Date date) {
            return this.setField(b -> b.orderDate = date);
        }

        public Builder divyDate(Date date) {
            return this.setField(b -> b.divyDate = date);
        }

        public Builder transactionProducts(List<TransactionProduction> t) {
            return this.setField(b -> b.transactionProductions = t);
        }
    }
}