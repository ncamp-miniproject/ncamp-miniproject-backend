package com.model2.mvc.purchase.domain;

import com.model2.mvc.common.Search;

public class BuyerIdLimitationSearch extends Search {
    private String buyerId;

    public BuyerIdLimitationSearch() {
        super();
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    @Override
    public String toString() {
        return "UserIdLimitationSearch{" + "buyerId='" + buyerId + '\'' + "} " + super.toString();
    }
}
