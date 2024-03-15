package com.model2.mvc.purchase.dto.request;

import com.model2.mvc.purchase.domain.PaymentOption;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdatePurchaseRequestDTO {
    private int tranNo;
    private String buyerId;
    private PaymentOption paymentOption;
    private String receiverName;
    private String receiverPhone;
    private String divyAddr;
    private String divyRequest;
    private LocalDate divyDate;
}
