package com.model2.mvc.product.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.model2.mvc.common.binder.jackson.LocalDateDeserializer;
import com.model2.mvc.common.binder.jackson.LocalDateSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CreateProductRequestDto {
    private String prodName;
    private String prodDetail;
    private int price;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate manuDate;
    private int stock;
    private String imageName;
    private String base64ImageData;
    private Integer categoryNo;

    public CreateProductRequestDto(){
    }

    @Builder
    public CreateProductRequestDto(String prodName,
                                   String prodDetail,
                                   int price,
                                   LocalDate manuDate,
                                   int stock,
                                   String imageName,
                                   String base64ImageData,
                                   Integer categoryNo) {
        this.prodName = prodName;
        this.prodDetail = prodDetail;
        this.price = price;
        this.manuDate = manuDate;
        this.stock = stock;
        this.imageName = imageName;
        this.base64ImageData = base64ImageData;
        this.categoryNo = categoryNo;
    }
}
