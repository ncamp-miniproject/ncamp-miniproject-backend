package com.model2.mvc.product.dto.response;

import com.model2.mvc.product.domain.Product;
import com.model2.mvc.user.domain.User;

import java.sql.Date;

public class GetProductResponseDTO {
    private int prodNo;
    private String fileName;
    private Date manuDate;
    private int price;
    private String prodDetail;
    private String prodName;
    private Date regDate;
    private int stock;
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
        return dto;
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

    public boolean isPurchasable() {
        return purchasable;
    }

    public void setPurchasable(boolean purchasable) {
        this.purchasable = purchasable;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public String toString() {
        return "GetProductResponseDTO{" + "prodNo=" + prodNo + ", fileName='" + fileName + '\'' + ", manuDate=" +
               manuDate + ", price=" + price + ", prodDetail='" + prodDetail + '\'' + ", prodName='" + prodName + '\'' +
               ", regDate=" + regDate + ", stock=" + stock + '}';
    }
}
