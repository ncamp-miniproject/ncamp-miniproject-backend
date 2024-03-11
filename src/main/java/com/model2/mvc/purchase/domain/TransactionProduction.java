package com.model2.mvc.purchase.domain;

import com.model2.mvc.product.domain.Product;

public class TransactionProduction {
    private Integer tranNo;
    private Product product;
    private int quantity;

    public TransactionProduction() {
    }

    public TransactionProduction(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public TransactionProduction(int tranNo, Product product, int quantity) {
        this.tranNo = tranNo;
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getTranNo() {
        return tranNo;
    }

    public void setTranNo(Integer tranNo) {
        this.tranNo = tranNo;
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
        return "TransactionProduction{" + "tranNo=" + tranNo + ", product=" + product + ", quantity=" + quantity + '}';
    }
}
