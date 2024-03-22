package com.model2.mvc.product.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.model2.mvc.common.binder.jackson.LocalDateDeserializer;
import com.model2.mvc.common.binder.jackson.LocalDateSerializer;
import com.model2.mvc.category.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    private int prodNo;
    private String prodName;
    private String prodDetail;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate manuDate;
    private Integer price;
    private String fileName;
    private Date regDate;
    private Integer stock;
    private Category category;

    public Product(int prodNo) {
        this.prodNo = prodNo;
    }

    public Product(int prodNo, Integer stock) {
        this.prodNo = prodNo;
        this.stock = stock;
    }

    public Product(String prodName, Integer price, Integer stock) {
        this.prodName = prodName;
        this.price = price;
        this.stock = stock;
    }

    public Product(int prodNo, String prodName, Integer price, Integer stock) {
        this(prodName, price, stock);
        this.prodNo = prodNo;
    }

    public void decrementStock(int howMuch) {
        this.stock -= howMuch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product)o;
        return prodNo == product.prodNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prodNo);
    }
}