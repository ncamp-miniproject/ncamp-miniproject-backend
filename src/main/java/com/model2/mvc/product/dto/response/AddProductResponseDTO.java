package com.model2.mvc.product.dto.response;

import com.model2.mvc.category.domain.Category;
import com.model2.mvc.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@ToString
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
}
