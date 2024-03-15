package com.model2.mvc.product.dto.response;

import com.model2.mvc.product.domain.Product;
import com.model2.mvc.user.domain.User;
import lombok.Getter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@ToString
public class GetProductResponseDTO {
    private int prodNo;
    private String fileName;
    private LocalDate manuDate;
    private int price;
    private String prodDetail;
    private String prodName;
    private Date regDate;
    private int stock;
    private String categoryName;
    private boolean purchasable;
    private User loginUser;

    private GetProductResponseDTO() {
    }

    public static GetProductResponseDTO from(Product product) {
        GetProductResponseDTO dto = new GetProductResponseDTO();
        dto.prodNo = product.getProdNo();
        dto.fileName = product.getFileName();
        dto.manuDate = product.getManuDate();
        dto.price = product.getPrice();
        dto.prodDetail = product.getProdDetail();
        dto.prodName = product.getProdName();
        dto.regDate = product.getRegDate();
        dto.stock = product.getStock();
        dto.categoryName = product.getCategory().getCategoryName();
        return dto;
    }

    public void setPurchasable(boolean purchasable) {
        this.purchasable = purchasable;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }
}
