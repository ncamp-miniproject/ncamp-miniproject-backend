package com.model2.mvc.product.domain;

import com.model2.mvc.common.Buildable;
import com.model2.mvc.common.BuilderTemplate;

import java.sql.Date;
import java.util.Objects;

public class Product implements Buildable<Product.Builder> {
    private int prodNo;
    private String fileName;
    private Date manuDate;
    private int price;
    private String prodDetail;
    private String prodName;
    private Date regDate;
    private int stock;

    public Product() {
    }

    private Product(Builder from) {
        this.prodNo = from.prodNo;
        this.fileName = from.fileName;
        this.manuDate = from.manuDate;
        this.price = from.price;
        this.prodDetail = from.prodDetail;
        this.prodName = from.prodName;
        this.regDate = from.regDate;
        this.stock = from.stock;
    }

    public Builder builder() {
        return new Builder(this);
    }

    public int getProdNo() {
        return prodNo;
    }

    public String getFileName() {
        return fileName;
    }

    public Date getManuDate() {
        return manuDate;
    }

    public int getPrice() {
        return price;
    }

    public String getProdDetail() {
        return prodDetail;
    }

    public String getProdName() {
        return prodName;
    }

    public Date getRegDate() {
        return regDate;
    }

    public int getStock() {
        return stock;
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

    @Override
    public String toString() {
        return "Product{" +
               "prodNo=" +
               prodNo +
               ", fileName='" +
               fileName +
               '\'' +
               ", manuDate=" +
               manuDate +
               ", price=" +
               price +
               ", prodDetail='" +
               prodDetail +
               '\'' +
               ", prodName='" +
               prodName +
               '\'' +
               ", regDate=" +
               regDate +
               ", stock=" +
               stock +
               '}';
    }

    public static class Builder extends BuilderTemplate<Product> {
        private String fileName;
        private Date manuDate;
        private int price;
        private String prodDetail;
        private String prodName;
        private int prodNo;
        private Date regDate;
        private int stock;

        public Builder(Product from) {
            this.fileName = from.fileName;
            this.manuDate = from.manuDate;
            this.price = from.price;
            this.prodDetail = from.prodDetail;
            this.prodName = from.prodName;
            this.prodNo = from.prodNo;
            this.regDate = from.regDate;
            this.stock = from.stock;
        }

        public Builder fileName(String fileName) {
            return setField(b -> b.fileName = fileName);
        }

        public Builder manuDate(Date manuDate) {
            return setField(b -> b.manuDate = manuDate);
        }

        public Builder price(int price) {
            return setField(b -> b.price = price);
        }

        public Builder prodDetail(String prodDetail) {
            return setField(b -> b.prodDetail = prodDetail);
        }

        public Builder prodName(String prodName) {
            return setField(b -> b.prodName = prodName);
        }

        public Builder prodNo(int prodNo) {
            return setField(b -> b.prodNo = prodNo);
        }

        public Builder regDate(Date regDate) {
            System.out.println("regDate=" + regDate);
            return setField(b -> b.regDate = regDate);
        }

        public Builder stock(int stock) {
            System.out.println("stock=" + stock);
            return setField(b -> b.stock = stock);
        }

        public Product build() {
            return new Product(this);
        }
    }
}