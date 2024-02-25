package com.model2.mvc.product.dto.request;

import com.model2.mvc.common.Buildable;
import com.model2.mvc.common.BuilderTemplate;

public class AddProductRequestDTO implements Buildable<AddProductRequestDTO.Builder> {
    private String fileName;
    private String manuDate;
    private int price;
    private String prodDetail;
    private String prodName;
    private int stock;

    public AddProductRequestDTO() {
    }

    private AddProductRequestDTO(Builder from) {
        this.fileName = from.fileName;
        this.manuDate = from.manuDate;
        this.price = from.price;
        this.prodDetail = from.prodDetail;
        this.prodName = from.prodName;
        this.stock = from.stock;
    }

    public Builder builder() {
        return new Builder(this);
    }

    @Override
    public String toString() {
        return "AddProductRequestDTO{" + "fileName='" + fileName + '\'' + ", manuDate='" + manuDate + '\'' +
               ", price=" + price + ", prodDetail='" + prodDetail + '\'' + ", prodName='" + prodName + '\'' + '}';
    }

    public String getFileName() {
        return fileName;
    }

    public String getManuDate() {
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

    public int getStock() {
        return this.stock;
    }

    public static class Builder extends BuilderTemplate<AddProductRequestDTO> {
        private String fileName;
        private String manuDate;
        private int price;
        private String prodDetail;
        private String prodName;
        private int stock;

        private Builder(AddProductRequestDTO from) {
            this.fileName = from.fileName;
            this.manuDate = from.manuDate;
            this.price = from.price;
            this.prodDetail = from.prodDetail;
            this.prodName = from.prodName;
            this.stock = from.stock;
        }

        public Builder fileName(String f) {
            return super.setField(b -> b.fileName = f);
        }

        public Builder manuDate(String m) {
            return super.setField(b -> b.manuDate = m);
        }

        public Builder price(int p) {
            return super.setField(b -> b.price = p);
        }

        public Builder prodDetail(String p) {
            return super.setField(b -> b.prodDetail = p);
        }

        public Builder prodName(String p) {
            return super.setField(b -> b.prodName = p);
        }

        public Builder stock(int s) {
            return super.setField(b -> b.stock = s);
        }

        @Override
        public AddProductRequestDTO build() {
            return new AddProductRequestDTO(this);
        }
    }
}
