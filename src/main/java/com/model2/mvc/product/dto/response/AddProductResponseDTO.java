package com.model2.mvc.product.dto.response;

import com.model2.mvc.product.domain.Product;

import java.sql.Date;

public class AddProductResponseDTO {
    private String fileName;
    private Date manuDate;
    private int price;
    private String prodDetail;
    private String prodName;
    private Date regDate;
    private int stock;

    public static AddProductResponseDTO from(Product product) {
        AddProductResponseDTO dto = new AddProductResponseDTO();
        dto.fileName = product.getFileName();
        dto.manuDate = product.getManuDate();
        dto.price = product.getPrice();
        dto.prodDetail = product.getProdDetail();
        dto.prodName = product.getProdName();
        dto.regDate = product.getRegDate();
        dto.stock = product.getStock();
        return dto;
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
        return "AddProductResponseDTO{" + "fileName='" + fileName + '\'' + ", manuDate=" + manuDate + ", price=" +
               price + ", prodDetail='" + prodDetail + '\'' + ", prodName='" + prodName + '\'' + ", regDate=" +
               regDate + ", stock=" + stock + '}';
    }
}
