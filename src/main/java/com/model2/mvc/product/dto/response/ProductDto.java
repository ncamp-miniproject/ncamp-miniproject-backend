package com.model2.mvc.product.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.model2.mvc.common.binder.jackson.LocalDateDeserializer;
import com.model2.mvc.common.binder.jackson.LocalDateSerializer;
import com.model2.mvc.product.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;

@Builder
@Getter
@ToString
public class ProductDto {
    private int prodNo;
    private String prodName;
    private String prodDetail;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate manuDate;
    private Integer price;
    private String fileName;
    private Date regDate;
    private Integer stock;
    private Integer categoryNo;
    private String categoryName;

    public static ProductDto from(Product product) {
        return ProductDto.builder()
                .prodNo(product.getProdNo())
                .prodName(product.getProdName())
                .prodDetail(product.getProdDetail())
                .manuDate(product.getManuDate())
                .price(product.getPrice())
                .regDate(product.getRegDate())
                .stock(product.getStock())
                .categoryNo(product.getCategory() == null ? null : product.getCategory().getCategoryNo())
                .categoryName(product.getCategory() == null ? null : product.getCategory().getCategoryName())
                .build();
    }
}
