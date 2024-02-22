package com.model2.mvc.purchase.domain;

import com.model2.mvc.product.domain.Product;

public class TransactionProduction {
    private Product product;
    private int quantity;

    public TransactionProduction(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
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
        return "TransactionProduction [product=" +
               product +
               ", quantity=" +
               quantity +
               "]";
    }
}
