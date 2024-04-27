package com.model2.mvc.seller.dto.request;

import com.model2.mvc.common.dto.Base64ImageDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class SellerApplicationRequestDto {
    private String sellerId;
    private Base64ImageDto profileImage;
    private String profile;
}
