package com.model2.mvc.product.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.model2.mvc.common.binder.jackson.LocalDateDeserializer;
import com.model2.mvc.common.binder.jackson.LocalDateSerializer;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.domain.ProductImage;
import lombok.Getter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Getter
@ToString
public class GetProductResponseDto {
    private int prodNo;
    private String seller;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate manuDate;
    private int price;
    private String prodDetail;
    private String prodName;
    private Date regDate;
    private int stock;
    private String categoryName;
    private boolean purchasable;
    private List<ProductImage> productImages;

    private GetProductResponseDto() {
    }

    public static GetProductResponseDto from(Product product) {
        GetProductResponseDto dto = new GetProductResponseDto();
        dto.prodNo = product.getProdNo();
        dto.seller = product.getSeller();
        dto.manuDate = product.getManuDate();
        dto.price = product.getPrice();
        dto.prodDetail = product.getProdDetail();
        dto.prodName = product.getProdName();
        dto.regDate = product.getRegDate();
        dto.stock = product.getStock();
        if (product.getCategory() != null) {
            dto.categoryName = product.getCategory().getCategoryName();
        }
        dto.productImages = product.getProductImages();
        return dto;
    }

    public void setPurchasable(boolean purchasable) {
        this.purchasable = purchasable;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
