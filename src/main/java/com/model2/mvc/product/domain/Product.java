package com.model2.mvc.product.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Product {
    private int prodNo;
    private String prodName;
    private String prodDetail;
    private LocalDate manuDate;
    private Integer price;
    private String fileName;
    private Date regDate;
    private Integer stock;

    public Product() {
    }

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

    public LocalDate getManuDate() {
        return manuDate;
    }

    public void setManuDate(LocalDate manuDate) {
        this.manuDate = manuDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void decrementStock(int quantityToDecr) {
        this.stock -= quantityToDecr;
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