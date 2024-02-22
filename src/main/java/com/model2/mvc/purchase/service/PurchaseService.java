package com.model2.mvc.purchase.service;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.purchase.domain.Purchase;

public interface PurchaseService {
    
    public static PurchaseService getInstance() {
        return PurchaseServiceImpl.getInstance();
    }

    public Purchase addPurchase(Purchase purchaseVO);
    
    public Purchase getPurchase(int tranNo);
    
    public Map<String, Object> getPurchaseList(Search searchVO, String userId);
    
    public Map<String, Object> getSaleList(Search searchVO);
    
    public Purchase updatePurchase(Purchase purchaseVO);
    
    public void updateTranCode(Purchase purchaseVO);
}
