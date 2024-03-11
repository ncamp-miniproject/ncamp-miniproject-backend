package com.model2.mvc.purchase.dao;

import com.model2.mvc.common.ListData;
import com.model2.mvc.purchase.domain.Purchase;

import java.util.Map;
import java.util.Optional;

public interface PurchaseDAO {

    public Optional<Purchase> findById(int tranNo);

    public ListData<Purchase> findAllInPageSize(int startRowNum, int endRowNum);

    public ListData<Purchase> findPurchasesByUserId(Map<String, Object> purchaseSearch);

    public boolean insertPurchase(Purchase purchase);

    public boolean updatePurchase(Purchase purchase);

    public boolean updateTranCode(Purchase purchase);
}
