package com.model2.mvc.product.dto.response;

import com.model2.mvc.product.domain.Product;

import java.sql.Date;

public class UpdateProductResponseDTO {
    private int prodNo;
    private String fileName;
    private Date manuDate;
    private int price;
    private String prodDetail;
    private String prodName;
    private Date regDate;
    private int stock;

    private UpdateProductResponseDTO() {
    }

    public static UpdateProductResponseDTO from(Product product) {
        UpdateProductResponseDTO responseDTO = new UpdateProductResponseDTO();
        responseDTO.prodNo = product.getProdNo();
        responseDTO.fileName = product.getFileName();
        responseDTO.manuDate = product.getManuDate();
        responseDTO.price = product.getPrice();
        responseDTO.prodDetail = product.getProdDetail();
        responseDTO.prodName = product.getProdName();
        responseDTO.regDate = product.getRegDate();
        responseDTO.stock = product.getStock();
        return responseDTO;
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
        return "UpdateProductResponseDTO{" +
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
