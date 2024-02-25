package com.model2.mvc.purchase.service;

import java.util.Map;

import com.model2.mvc.common.dto.Search;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.dto.response.ListPurchaseResponseDTO;
import com.model2.mvc.user.domain.User;

public interface PurchaseService {
    
    public static PurchaseService getInstance() {
        return PurchaseServiceImpl.getInstance();
    }

    public Purchase addPurchase(Purchase purchaseVO);
    
    public Purchase getPurchase(int tranNo);
    
    public ListPurchaseResponseDTO getPurchaseList(Search searchVO, User user);
    
    public Map<String, Object> getSaleList(Search searchVO);
    
    public Purchase updatePurchase(Purchase purchaseVO);
    
    public void updateTranCode(Purchase purchaseVO);
}
