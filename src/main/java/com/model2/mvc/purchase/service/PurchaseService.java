package com.model2.mvc.purchase.service;

import com.model2.mvc.common.dto.Search;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.dto.request.AddPurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.UpdatePurchaseRequestDTO;
import com.model2.mvc.purchase.dto.response.AddPurchaseResponseDTO;
import com.model2.mvc.purchase.dto.response.GetPurchaseResponseDTO;
import com.model2.mvc.purchase.dto.response.ListPurchaseResponseDTO;
import com.model2.mvc.user.domain.User;

import java.util.Map;

public interface PurchaseService {

    public static PurchaseService getInstance() {
        return PurchaseServiceImpl.getInstance();
    }

    public AddPurchaseResponseDTO addPurchase(AddPurchaseRequestDTO requestDTO);

    public GetPurchaseResponseDTO getPurchase(int tranNo);

    public ListPurchaseResponseDTO getPurchaseList(Search searchVO, User user);

    public Map<String, Object> getSaleList(Search searchVO);

    public Purchase updatePurchase(UpdatePurchaseRequestDTO requestDTO);

    public void updateTranCode(Purchase purchaseVO);
}
