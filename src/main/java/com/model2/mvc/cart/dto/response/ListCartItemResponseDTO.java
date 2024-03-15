package com.model2.mvc.cart.dto.response;

import com.model2.mvc.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class ListCartItemResponseDTO {
    private int priceSum;
    private int itemCount;
    private Map<Product, Integer> productsInCart;
}
