package com.model2.mvc.purchase.dto.response;

import com.model2.mvc.purchase.domain.PaymentOption;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TransactionProduction;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class AddPurchaseResponseDTO {
    private String buyerId;
    private PaymentOption paymentOption;
    private String receiverName;
    private String receiverPhone;
    private String divyAddr;
    private String divyRequest;
    private LocalDate divyDate;
    private List<TransactionProduction> transactionProductions;

    private AddPurchaseResponseDTO() {
    }

    public static AddPurchaseResponseDTO from(Purchase purchase) {
        AddPurchaseResponseDTO dto = new AddPurchaseResponseDTO();
        dto.buyerId = purchase.getBuyer().getUserId();
        dto.paymentOption = purchase.getPaymentOption();
        dto.receiverName = purchase.getReceiverName();
        dto.receiverPhone = purchase.getReceiverPhone();
        dto.divyAddr = purchase.getDivyAddr();
        dto.divyRequest = purchase.getDivyRequest();
        dto.divyDate = purchase.getDivyDate();
        dto.transactionProductions = purchase.getTransactionProductions();
        return dto;
    }

    public String getBuyerId() {
        return buyerId;
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

    public LocalDate getDivyDate() {
        return divyDate;
    }

    public List<TransactionProduction> getTransactionProductions() {
        return Collections.unmodifiableList(this.transactionProductions);
    }

    @Override
    public String toString() {
        return "AddPurchaseResponseDTO{" +
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
               ", divyDate='" +
               divyDate +
               '\'' +
               ", transactionProductions=" +
               transactionProductions +
               '}';
    }
}
