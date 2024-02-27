package com.model2.mvc.purchase.dto.request;

import com.model2.mvc.product.domain.Product;
import com.model2.mvc.user.domain.User;

import java.util.Map;

public class AddPurchaseViewResponseDTO {
    private int priceSum;
    private int quantitySum;
    private int productCount;
    private Map<Product, Integer> productsToPurchase;
    private User purchaseUser;

    public AddPurchaseViewResponseDTO(int priceSum,
                                      int quantitySum,
                                      int productCount,
                                      Map<Product, Integer> productsToPurchase) {
        this.priceSum = priceSum;
        this.quantitySum = quantitySum;
        this.productCount = productCount;
        this.productsToPurchase = productsToPurchase;
    }

    public int getPriceSum() {
        return priceSum;
    }

    public int getQuantitySum() {
        return quantitySum;
    }

    public int getProductCount() {
        return productCount;
    }

    public Map<Product, Integer> getProductsToPurchase() {
        return productsToPurchase;
    }

    public User getPurchaseUser() {
        return purchaseUser;
    }

    public void setPurchaseUser(User loginUser) {
        this.purchaseUser = loginUser;
    }

    @Override
    public String toString() {
        return "AddPurchaseViewResponseDTO{" +
               "priceSum=" +
               priceSum +
               ", quantitySum=" +
               quantitySum +
               ", productCount=" +
               productCount +
               ", productsToPurchase=" +
               productsToPurchase +
               '}';
    }
}
