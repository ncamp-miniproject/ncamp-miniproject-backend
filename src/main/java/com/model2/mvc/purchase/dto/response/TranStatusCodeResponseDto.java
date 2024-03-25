package com.model2.mvc.purchase.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class TranStatusCodeResponseDto {
    private final String code;
    private final String status;
}
