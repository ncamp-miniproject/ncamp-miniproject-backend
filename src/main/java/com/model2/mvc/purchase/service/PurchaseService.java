package com.model2.mvc.purchase.service;

import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.dto.request.AddPurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.AddPurchaseViewResponseDTO;
import com.model2.mvc.purchase.dto.request.ListPurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.UpdatePurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.UpdateTranCodeRequestDTO;
import com.model2.mvc.purchase.dto.response.AddPurchaseResponseDTO;
import com.model2.mvc.purchase.dto.response.GetPurchaseResponseDTO;
import com.model2.mvc.purchase.dto.response.ListPurchaseResponseDTO;

import java.util.Map;

public interface PurchaseService {

    public AddPurchaseResponseDTO addPurchase(AddPurchaseRequestDTO requestDTO);

    public GetPurchaseResponseDTO getPurchase(int tranNo);

    public ListPurchaseResponseDTO getPurchaseList(ListPurchaseRequestDTO requestDTO, String loginUserId);

    public AddPurchaseViewResponseDTO getProductsWithQuantity(Map<Integer, Integer> prodNoQuantityMap);

    public ListPurchaseResponseDTO getSaleList(int page, int pageSize);

    public Purchase updatePurchase(UpdatePurchaseRequestDTO requestDTO);

    public void updateTranCode(UpdateTranCodeRequestDTO purchaseVO);
}
