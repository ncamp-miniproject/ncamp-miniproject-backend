package com.model2.mvc.service.purchase.domain;

import java.sql.Date;

import com.model2.mvc.common.exception.ModifyInitializedFieldException;
import com.model2.mvc.service.product.domain.Product;
import com.model2.mvc.service.user.domain.User;

public class Purchase {

    private User buyer = null;
    private String divyAddr = null;
    private String divyDate = null;
    private String divyRequest = null;
    private Date orderDate = null;
    private String paymentOption = null;
    private Product purchaseProd = null;
    private String receiverName = null;
    private String receiverPhone = null;
    private String tranCode = null;
    private Integer tranNo = null;
    private String tranStatus = null;

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

    public Product getPurchaseProd() {
        return purchaseProd;
    }

    public void setPurchaseProd(Product purchaseProd) {
        setInvariant(this.purchaseProd);
        this.purchaseProd = purchaseProd;
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

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        setInvariant(this.tranCode);
        this.tranCode = tranCode;
    }

    public int getTranNo() {
        return tranNo;
    }

    public void setTranNo(int tranNo) {
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
               ", purchaseProd=" +
               purchaseProd +
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