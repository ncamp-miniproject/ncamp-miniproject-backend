package com.model2.mvc.cart.dto.response;

import com.model2.mvc.product.domain.Product;

import java.util.List;
import java.util.Map;

public class ListCartItemResponseDTO {
    private int priceSum;
    private int itemCount;
    private Map<Product, Integer> productsInCart;

    public ListCartItemResponseDTO(int priceSum, int itemCount, Map<Product, Integer> productsInCart) {
        this.priceSum = priceSum;
        this.itemCount = itemCount;
        this.productsInCart = productsInCart;
    }

    public int getPriceSum() {
        return priceSum;
    }

    public int getItemCount() {
        return itemCount;
    }

    public Map<Product, Integer> getProductsInCart() {
        return productsInCart;
    }

    @Override
    public String toString() {
        return "ListCartItemResponseDTO{" +
               "priceSum=" +
               priceSum +
               ", itemCount=" +
               itemCount +
               ", productsInCart=" +
               productsInCart +
               '}';
    }
}
