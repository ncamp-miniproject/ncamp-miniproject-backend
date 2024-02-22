package com.model2.mvc.purchase.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.model2.mvc.common.exception.ModifyInitializedFieldException;
import com.model2.mvc.user.domain.User;

public class Purchase {
    private Integer tranNo = null;
    private User buyer = null;
    private String paymentOption = null;
    private String receiverName = null;
    private String receiverPhone = null;
    private String divyAddr = null;
    private String divyRequest = null;
    private TranCode tranCode = null;
    private Date orderDate = null;
    private String divyDate = null;
    private String tranStatus = null;
    private List<TransactionProduction> transactionProductions = new ArrayList<>();

    public Purchase() {
    }
    
    private void setInvariant(Object toCheck) {
        if (toCheck != null) {
            throw new ModifyInitializedFieldException();
        }
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        setInvariant(this.buyer);
        this.buyer = buyer;
    }

    public String getDivyAddr() {
        return divyAddr;
    }

    public void setDivyAddr(String divyAddr) {
        setInvariant(this.divyAddr);
        this.divyAddr = divyAddr;
    }

    public String getDivyDate() {
        return divyDate;
    }

    public void setDivyDate(String divyDate) {
        setInvariant(this.divyDate);
        this.divyDate = divyDate;
    }

    public String getDivyRequest() {
        return divyRequest;
    }

    public void setDivyRequest(String divyRequest) {
        setInvariant(this.divyRequest);
        this.divyRequest = divyRequest;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        setInvariant(this.orderDate);
        this.orderDate = orderDate;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        setInvariant(this.paymentOption);
        this.paymentOption = paymentOption;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        setInvariant(this.receiverName);
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        setInvariant(this.receiverPhone);
        this.receiverPhone = receiverPhone;
    }

    public TranCode getTranCode() {
        return tranCode;
    }

    public void setTranCode(TranCode tranCode) {
        setInvariant(this.tranCode);
        this.tranCode = tranCode;
    }

    public Integer getTranNo() {
        return tranNo;
    }

    public void setTranNo(Integer tranNo) {
        setInvariant(this.tranNo);
        this.tranNo = tranNo;
    }

    public String getTranStatus() {
        return tranStatus;
    }

    public void setTranStatus(String tranStatus) {
        setInvariant(this.tranStatus);
        this.tranStatus = tranStatus;
    }

    public List<TransactionProduction> getTransactionProductions() {
        return Collections.unmodifiableList(this.transactionProductions);
    }

    public void addTransactionProductions(TransactionProduction transactionProductions) {
        this.transactionProductions.add(transactionProductions);
    }

    @Override
    public String toString() {
        return "Purchase [buyer=" +
               buyer +
               ", divyAddr=" +
               divyAddr +
               ", divyDate=" +
               divyDate +
               ", divyRequest=" +
               divyRequest +
               ", orderDate=" +
               orderDate +
               ", paymentOption=" +
               paymentOption +
               ", receiverName=" +
               receiverName +
               ", receiverPhone=" +
               receiverPhone +
               ", tranCode=" +
               tranCode +
               ", tranNo=" +
               tranNo +
               ", tranStatus=" +
               tranStatus +
               "]";
    }
}