package com.model2.mvc.product.domain;

import java.sql.Date;
import java.util.Objects;

public class Product {
    private int prodNo;
    private String prodName;
    private String prodDetail;
    private Date manuDate;
    private int price;
    private String fileName;
    private Date regDate;
    private int stock;

    public Product() {
    }

    public Product(int prodNo) {
        this.prodNo = prodNo;
    }

    public int getProdNo() {
        return prodNo;
    }

    public void setProdNo(int prodNo) {
        this.prodNo = prodNo;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDetail() {
        return prodDetail;
    }

    public void setProdDetail(String prodDetail) {
        this.prodDetail = prodDetail;
    }

    public Date getManuDate() {
        return manuDate;
    }

    public void setManuDate(Date manuDate) {
        this.manuDate = manuDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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
               ", prodName='" +
               prodName +
               '\'' +
               ", prodDetail='" +
               prodDetail +
               '\'' +
               ", manuDate=" +
               manuDate +
               ", price=" +
               price +
               ", fileName='" +
               fileName +
               '\'' +
               ", regDate=" +
               regDate +
               ", stock=" +
               stock +
               '}';
    }
}