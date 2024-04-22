package com.model2.mvc.cart.dto.response;

import com.model2.mvc.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class ListCartItemResponseDto {
    private int priceSum;
    private int itemCount;
    private List<ProductsInCartResponseDto> productsInCart;

    @AllArgsConstructor
    @Getter
    @ToString
    public static class ProductsInCartResponseDto {
        private Product product;
        private Integer quantity;
    }
}
