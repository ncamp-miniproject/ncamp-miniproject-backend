package com.model2.mvc.purchase.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.model2.mvc.common.binder.jackson.LocalDateDeserializer;
import com.model2.mvc.common.binder.jackson.LocalDateSerializer;
import com.model2.mvc.purchase.domain.PaymentOption;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TranStatusCode;
import com.model2.mvc.purchase.domain.TransactionProduction;
import com.model2.mvc.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@ToString
public class GetPurchaseResponseDto {
    private Integer tranNo;
    private User buyer;
    private PaymentOption paymentOption;
    private String receiverName;
    private String receiverPhone;
    private String divyAddr;
    private String divyRequest;
    private TranStatusCodeResponseDto tranStatusCode;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate orderDate;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate divyDate;
    private List<TransactionProduction> transactionProductions;

    private GetPurchaseResponseDto(Purchase from) {
        this.tranNo = from.getTranNo();
        this.buyer = from.getBuyer();
        this.paymentOption = from.getPaymentOption();
        this.receiverName = from.getReceiverName();
        this.receiverPhone = from.getReceiverPhone();
        this.divyAddr = from.getDivyAddr();
        this.divyRequest = from.getDivyRequest();
        TranStatusCode tsc = from.getTranStatusCode();
        this.tranStatusCode = new TranStatusCodeResponseDto(tsc.getCode(), tsc.getStatus());
        this.orderDate = from.getOrderDate();
        this.divyDate = from.getDivyDate();
        this.transactionProductions = from.getTransactionProductions();
    }

    public static GetPurchaseResponseDto from(Purchase purchase) {
        return new GetPurchaseResponseDto(purchase);
    }
}
