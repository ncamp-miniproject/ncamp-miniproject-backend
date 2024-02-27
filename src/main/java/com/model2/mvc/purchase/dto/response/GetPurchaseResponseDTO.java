package com.model2.mvc.purchase.dto.response;

import com.model2.mvc.purchase.domain.PaymentOption;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TranStatusCode;
import com.model2.mvc.purchase.domain.TransactionProduction;
import com.model2.mvc.user.domain.User;

import java.sql.Date;
import java.util.List;

public class GetPurchaseResponseDTO {
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

    private GetPurchaseResponseDTO(Purchase from) {
        this.tranNo = from.getTranNo();
        this.buyer = from.getBuyer();
        this.paymentOption = from.getPaymentOption();
        this.receiverName = from.getReceiverName();
        this.receiverPhone = from.getReceiverPhone();
        this.divyAddr = from.getDivyAddr();
        this.divyRequest = from.getDivyRequest();
        this.tranStatusCode = from.getTranStatusCode();
        this.orderDate = from.getOrderDate();
        this.divyDate = from.getDivyDate();
        this.transactionProductions = from.getTransactionProductions();
    }

    public static GetPurchaseResponseDTO from(Purchase purchase) {
        return new GetPurchaseResponseDTO(purchase);
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

    @Override
    public String toString() {
        return "GetPurchaseResponseDTO{" +
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
