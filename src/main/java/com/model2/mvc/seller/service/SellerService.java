package com.model2.mvc.seller.service;

import com.model2.mvc.seller.dto.request.SellerApplicationRequestDto;
import com.model2.mvc.seller.dto.response.SellerInfoResponseDto;

public interface SellerService {

    public void submitApplication(SellerApplicationRequestDto requestDto, String filePath);

    public void acceptApplication(String sellerId);

    public SellerInfoResponseDto getSellerInfo(String sellerId);

    public void rejectApplication(String sellerId);
}
