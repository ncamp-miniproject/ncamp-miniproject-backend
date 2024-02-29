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

    public Product(int prodNo,
                   String fileName,
                   Date manuDate,
                   int price,
                   String prodDetail,
                   String prodName,
                   Date regDate,
                   int stock) {
        this.prodNo = prodNo;
        this.fileName = fileName;
        this.manuDate = manuDate;
        this.price = price;
        this.prodDetail = prodDetail;
        this.prodName = prodName;
        this.regDate = regDate;
        this.stock = stock;
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