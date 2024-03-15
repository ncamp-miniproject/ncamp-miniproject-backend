package com.model2.mvc.purchase.dto.request;

import com.model2.mvc.purchase.domain.TranStatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class UpdateTranCodeRequestDTO {
    private int tranNo;
    private TranStatusCode tranStatusCode;
}
