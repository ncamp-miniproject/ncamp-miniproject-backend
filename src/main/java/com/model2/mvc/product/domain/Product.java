package com.model2.mvc.product.domain;

import java.sql.Date;

public class Product {
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

    public Product(int prodNo) {
        this.prodNo = prodNo;
    }

    public int getProdNo() {
        return prodNo;
    }

    public void setProdNo(int prodNo) {
        this.prodNo = prodNo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public String getProdDetail() {
        return prodDetail;
    }

    public void setProdDetail(String prodDetail) {
        this.prodDetail = prodDetail;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
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
}