package com.model2.mvc.purchase.domain;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.product.domain.Product;

import java.util.Arrays;
import java.util.List;

public class TransactionProduction {
    private Product product;
    private int quantity;

    public TransactionProduction(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public static List<TransactionProduction> from(String[] values) {
        return Arrays.stream(values)
                .map(v -> v.split(CommonConstants.QUERY_VALUE_DELIMITER))
                .map(s -> new TransactionProduction(new Product().builder().prodNo(Integer.parseInt(s[0])).build(),
                                                    Integer.parseInt(s[1])))
                .toList();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "TransactionProduction [product=" + product + ", quantity=" + quantity + "]";
    }
}
