package com.model2.mvc.purchase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class PaymentOptionResponseDto {
    private String code;
    private String paymentName;
}
