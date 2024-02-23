package com.model2.mvc.purchase.domain;

import java.util.Collections;
import java.util.List;

public class PurchaseList {
    private final int count;
    private final List<Purchase> purchaseList;

    public PurchaseList(int count, List<Purchase> purchaseList) {
        this.count = count;
        this.purchaseList = purchaseList;
    }

    public int getCount() {
        return this.count;
    }

    public List<Purchase> getPurchaseList() {
        return Collections.unmodifiableList(this.purchaseList);
    }

    @Override
    public String toString() {
        return "PurchaseData{" + "count=" + count + ", purchaseList=" + purchaseList + '}';
    }
}
