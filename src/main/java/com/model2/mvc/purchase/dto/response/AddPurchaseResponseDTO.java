package com.model2.mvc.purchase.dto.response;

import com.model2.mvc.purchase.domain.PaymentOption;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TransactionProduction;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@ToString
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
}
