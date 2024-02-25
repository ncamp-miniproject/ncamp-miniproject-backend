package com.model2.mvc.product.dto.request;

import com.model2.mvc.common.Buildable;
import com.model2.mvc.common.BuilderTemplate;

public class UpdateProductRequestDTO implements Buildable<UpdateProductRequestDTO.Builder> {
    private int prodNo;
    private String prodName;
    private String prodDetail;
    private String manuDate;
    private int price;
    private String fileName;
    private int stock;

    public UpdateProductRequestDTO() {
    }

    private UpdateProductRequestDTO(Builder from) {
        this.prodNo = from.prodNo;
        this.prodName = from.prodName;
        this.prodDetail = from.prodDetail;
        this.manuDate = from.manuDate;
        this.price = from.price;
        this.fileName = from.fileName;
        this.stock = from.stock;
    }

    @Override
    public Builder builder() {
        return new Builder(this);
    }

    public int getProdNo() {
        return prodNo;
    }

    public String getProdName() {
        return prodName;
    }

    public String getProdDetail() {
        return prodDetail;
    }

    public String getManuDate() {
        return manuDate;
    }

    public int getPrice() {
        return price;
    }

    public String getFileName() {
        return fileName;
    }

    public int getStock() {
        return this.stock;
    }

    @Override
    public String toString() {
        return "UpdateProductRequestDTO{" +
               "prodNo=" +
               prodNo +
               ", prodName='" +
               prodName +
               '\'' +
               ", prodDetail='" +
               prodDetail +
               '\'' +
               ", manuDate='" +
               manuDate +
               '\'' +
               ", price=" +
               price +
               ", fileName='" +
               fileName +
               '\'' +
               ", stock=" +
               stock +
               '}';
    }

    public static class Builder extends BuilderTemplate<UpdateProductRequestDTO> {
        private int prodNo;
        private String prodName;
        private String prodDetail;
        private String manuDate;
        private int price;
        private String fileName;
        private int stock;

        private Builder(UpdateProductRequestDTO from) {
            this.prodNo = from.prodNo;
            this.prodName = from.prodName;
            this.prodDetail = from.prodDetail;
            this.manuDate = from.manuDate;
            this.price = from.price;
            this.fileName = from.fileName;
            this.stock = from.stock;
        }

        public Builder prodNo(int p) {
            return super.setField(b -> b.price = p);
        }

        public Builder prodName(String p) {
            return super.setField(b -> b.prodName = p);
        }

        public Builder prodDetail(String p) {
            return super.setField(b -> b.prodDetail = p);
        }

        public Builder manuDate(String m) {
            return super.setField(b -> b.manuDate = m);
        }

        public Builder price(int p) {
            return super.setField(b -> b.price = p);
        }

        public Builder fileName(String f) {
            return super.setField(b -> b.fileName = f);
        }

        public Builder stock(int s) {
            return super.setField(b -> b.stock = s);
        }

        @Override
        public UpdateProductRequestDTO build() {
            return new UpdateProductRequestDTO(this);
        }
    }
}
