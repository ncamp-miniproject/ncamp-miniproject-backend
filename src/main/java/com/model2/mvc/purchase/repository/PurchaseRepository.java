package com.model2.mvc.purchase.repository;

import com.model2.mvc.common.ListData;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TranStatusCode;

import java.util.Map;
import java.util.Optional;

public interface PurchaseRepository {

    public Optional<Purchase> findById(int tranNo);

    public ListData<Purchase> findAllInPageSize(int startRowNum, int endRowNum);

    public ListData<Purchase> findPurchasesByUserId(Map<String, Object> purchaseSearch);

    public boolean insertPurchase(Purchase purchase);

    public boolean updatePurchase(Purchase purchase);

    public boolean updateTranCode(Purchase purchase);

    public TranStatusCode findTranStatusCodeByTranNo(int tranNo);
}
