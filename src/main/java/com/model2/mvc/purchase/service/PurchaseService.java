package com.model2.mvc.purchase.service;

import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.dto.request.AddPurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.AddPurchaseViewResponseDTO;
import com.model2.mvc.purchase.dto.request.ListPurchaseRequestDto;
import com.model2.mvc.purchase.dto.request.UpdatePurchaseRequestDto;
import com.model2.mvc.purchase.dto.request.UpdateTranCodeRequestDTO;
import com.model2.mvc.purchase.dto.response.AddPurchaseResponseDTO;
import com.model2.mvc.purchase.dto.response.GetPurchaseResponseDto;
import com.model2.mvc.purchase.dto.response.ListPurchaseResponseDto;

import java.util.Map;

public interface PurchaseService {

    public AddPurchaseResponseDTO addPurchase(AddPurchaseRequestDTO requestDTO);

    public GetPurchaseResponseDto getPurchase(int tranNo);

    public ListPurchaseResponseDto getPurchaseList(ListPurchaseRequestDto requestDTO);

    public AddPurchaseViewResponseDTO getProductsWithQuantity(Map<Integer, Integer> prodNoQuantityMap);

    public ListPurchaseResponseDto getSaleList(Integer page, Integer pageSize);

    public Purchase updatePurchase(int tranNo, UpdatePurchaseRequestDto requestDTO);

    public void updateTranCode(UpdateTranCodeRequestDTO purchaseVO);
}
