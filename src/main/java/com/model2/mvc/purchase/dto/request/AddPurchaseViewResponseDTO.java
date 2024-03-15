package com.model2.mvc.purchase.dto.request;

import com.model2.mvc.product.domain.Product;
import com.model2.mvc.user.domain.User;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
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

    public void setPurchaseUser(User loginUser) {
        this.purchaseUser = loginUser;
    }
}
