package com.model2.mvc.purchase.domain;

import com.model2.mvc.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TransactionProduction {
    private Integer tranNo;
    private Product product;
    private int quantity;

    public TransactionProduction(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
