package com.model2.mvc.product.dto.response;

import com.model2.mvc.category.domain.Category;
import com.model2.mvc.product.domain.Product;

import java.sql.Date;
import java.time.LocalDate;

public class AddProductResponseDTO {
    private Integer prodNo;
    private String fileName;
    private LocalDate manuDate;
    private int price;
    private String prodDetail;
    private String prodName;
    private Date regDate;
    private int stock;
    private Category category;

    public static AddProductResponseDTO from(Product product) {
        AddProductResponseDTO dto = new AddProductResponseDTO();
        dto.prodNo = product.getProdNo();
        dto.fileName = product.getFileName();
        dto.manuDate = product.getManuDate();
        dto.price = product.getPrice();
        dto.prodDetail = product.getProdDetail();
        dto.prodName = product.getProdName();
        dto.regDate = product.getRegDate();
        dto.stock = product.getStock();
        dto.category = product.getCategory();
        return dto;
    }

    public Integer getProdNo() {
        return prodNo;
    }

    public String getFileName() {
        return fileName;
    }

    public LocalDate getManuDate() {
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

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "AddProductResponseDTO{" +
               "fileName='" +
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
