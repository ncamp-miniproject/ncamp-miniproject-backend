package com.model2.mvc.product.dto.request;

import com.model2.mvc.common.Buildable;
import com.model2.mvc.common.BuilderTemplate;

public class UpdateProductRequestDTO {
    private int prodNo;
    private String prodName;
    private String prodDetail;
    private String manuDate;
    private int price;
    private String fileName;
    private int stock;

    public UpdateProductRequestDTO() {
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

    public String getManuDate() {
        return manuDate;
    }

    public void setManuDate(String manuDate) {
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "UpdateProductRequestDTO{" +
               "prodNo=" +
               prodNo +
               ", prodName='" +
               prodName +
               '\'' +
               ", prodDetail='" +
               prodDetail +
               '\'' +
               ", manuDate='" +
               manuDate +
               '\'' +
               ", price=" +
               price +
               ", fileName='" +
               fileName +
               '\'' +
               ", stock=" +
               stock +
               '}';
    }
}
