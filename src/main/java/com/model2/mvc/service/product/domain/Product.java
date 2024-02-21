package com.model2.mvc.service.product.domain;

import java.sql.Date;

public class Product {

    private String fileName;
    private String manuDate;
    private int price;
    private String prodDetail;
    private String prodName;
    private int prodNo;
    private Date regDate;
    private String proTranCode;
    private String proTranStatus;

    public Product() {
    }
    
    public static ProductVOBuilder builder() {
        return new ProductVOBuilder();
    }
    
    public static class ProductVOBuilder implements Cloneable {
        private String fileName;
        private String manuDate;
        private int price;
        private String prodDetail;
        private String prodName;
        private int prodNo;
        private Date regDate;
        private String proTranCode;
        
        public ProductVOBuilder() {
            this.fileName = null;
            this.manuDate = null;
            this.price = 0;
            this.prodDetail = null;
            this.prodName = null;
            this.prodNo = 0;
            this.regDate = null;
            this.proTranCode = null;
        }
        
        private static interface FieldSetter {
            public void set(ProductVOBuilder builder);
        }
        
        public ProductVOBuilder fileName(String fileName) {
            return setField(b -> b.fileName = fileName);
        }
        
        private ProductVOBuilder setField(FieldSetter setMethod) {
            ProductVOBuilder instance;
            try {
                instance = (ProductVOBuilder)this.clone();
            } catch (CloneNotSupportedException e) {
                System.err.println(e.getMessage() + ": ProductVO.ProductVOBuilder");
                instance = this;
            }
            setMethod.set(instance);
            return instance;
        }
        
        public ProductVOBuilder manuDate(String manuDate) {
            return setField(b -> b.manuDate = manuDate);
        }
        
        public ProductVOBuilder price(int price) {
            return setField(b -> b.price = price);
        }
        
        public ProductVOBuilder prodDetail(String prodDetail) {
            return setField(b -> b.prodDetail = prodDetail);
        }
        
        public ProductVOBuilder prodName(String prodName) {
            return setField(b -> b.prodName = prodName);
        }
        
        public ProductVOBuilder prodNo(int prodNo) {
            return setField(b -> b.prodNo = prodNo);
        }
        
        public ProductVOBuilder regDate(Date regDate) {
            System.out.println("regDate=" + regDate);
            return setField(b -> b.regDate = regDate);
        }
        
        public ProductVOBuilder proTranCode(String proTranCode) {
            System.out.println("proTranCode=" + proTranCode);
            return setField(b -> b.proTranCode = proTranCode);
        }
        
        public Product build() {
            Product builded = new Product();
            builded.setFileName(this.fileName);
            builded.setManuDate(this.manuDate);
            builded.setPrice(this.price);
            builded.setProdDetail(this.prodDetail);
            builded.setProdName(this.prodName);
            builded.setProdNo(this.prodNo);
            builded.setProTranCode(this.proTranCode);
            builded.setRegDate(this.regDate);
            return builded;
        }
        
        @Override
        protected Object clone() throws CloneNotSupportedException {
            ProductVOBuilder clonedObject = new ProductVOBuilder();
            clonedObject.fileName = this.fileName;
            clonedObject.manuDate = this.manuDate;
            clonedObject.price = this.price;
            clonedObject.prodDetail = this.prodDetail;
            clonedObject.prodName = this.prodName;
            clonedObject.prodNo = this.prodNo;
            clonedObject.regDate = this.regDate;
            clonedObject.proTranCode = this.proTranCode;
            return clonedObject;
        }
    }

    public String getProTranCode() {
        return proTranCode;
    }

    public void setProTranCode(String proTranCode) {
        this.proTranCode = proTranCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getManuDate() {
        return manuDate;
    }

    public void setManuDate(String manuDate) {
        this.manuDate = manuDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProdDetail() {
        return prodDetail;
    }

    public void setProdDetail(String prodDetail) {
        this.prodDetail = prodDetail;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public int getProdNo() {
        return prodNo;
    }

    public void setProdNo(int prodNo) {
        this.prodNo = prodNo;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getProTranStatus() {
        return proTranStatus;
    }

    public void setProTranStatus(String proTranStatus) {
        this.proTranStatus = proTranStatus;
    }

    @Override
    public String toString() {
        return "Product [fileName=" +
               fileName +
               ", manuDate=" +
               manuDate +
               ", price=" +
               price +
               ", prodDetail=" +
               prodDetail +
               ", prodName=" +
               prodName +
               ", prodNo=" +
               prodNo +
               ", regDate=" +
               regDate +
               ", proTranCode=" +
               proTranCode +
               ", proTranStatus=" +
               proTranStatus +
               "]";
    }
}