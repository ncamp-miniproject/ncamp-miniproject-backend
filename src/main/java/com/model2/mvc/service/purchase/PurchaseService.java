package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.purchase.domain.Purchase;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

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
