package com.model2.mvc.purchase.dto.request;

import com.model2.mvc.common.Buildable;
import com.model2.mvc.common.BuilderTemplate;
import com.model2.mvc.purchase.domain.PaymentOption;
import com.model2.mvc.purchase.domain.TransactionProduction;

import java.sql.Date;
import java.util.List;

public class AddPurchaseRequestDTO implements Buildable<AddPurchaseRequestDTO.Builder> {
    private String buyerId;
    private PaymentOption paymentOption;
    private String receiverName;
    private String receiverPhone;
    private String divyAddr;
    private String divyRequest;
    private Date divyDate;
    private List<TransactionProduction> transactionProductions;

    public AddPurchaseRequestDTO() {
    }

    private AddPurchaseRequestDTO(Builder from) {
        this.buyerId = from.buyerId;
        this.paymentOption = from.paymentOption;
        this.receiverName = from.receiverName;
        this.receiverPhone = from.receiverPhone;
        this.divyAddr = from.divyAddr;
        this.divyRequest = from.divyRequest;
        this.divyDate = from.divyDate;
        this.transactionProductions = from.transactionProductions;
    }

    @Override
    public Builder builder() {
        return new Builder(this);
    }

    public String getBuyerId() {
        return buyerId;
    }

    public PaymentOption getPaymentOption() {
        return paymentOption;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public String getDivyAddr() {
        return divyAddr;
    }

    public String getDivyRequest() {
        return divyRequest;
    }

    public Date getDivyDate() {
        return divyDate;
    }

    public List<TransactionProduction> getTransactionProductions() {
        return transactionProductions;
    }

    @Override
    public String toString() {
        return "AddPurchaseRequestDTO{" + "buyerId='" + buyerId + '\'' + ", paymentOption=" + paymentOption +
               ", receiverName='" + receiverName + '\'' + ", receiverPhone='" + receiverPhone + '\'' + ", divyAddr='" +
               divyAddr + '\'' + ", divyRequest='" + divyRequest + '\'' + ", divyDate='" + divyDate + '\'' + '}';
    }

    public static class Builder extends BuilderTemplate<AddPurchaseRequestDTO> {
        private String buyerId;
        private PaymentOption paymentOption;
        private String receiverName;
        private String receiverPhone;
        private String divyAddr;
        private String divyRequest;
        private Date divyDate;
        private List<TransactionProduction> transactionProductions;

        private Builder(AddPurchaseRequestDTO from) {
            this.buyerId = from.buyerId;
            this.paymentOption = from.paymentOption;
            this.receiverName = from.receiverName;
            this.receiverPhone = from.receiverPhone;
            this.divyAddr = from.divyAddr;
            this.divyRequest = from.divyRequest;
            this.divyDate = from.divyDate;
            this.transactionProductions = from.transactionProductions;
        }

        public Builder buyerId(String u) {
            return super.setField(b -> b.buyerId = u);
        }

        public Builder paymentOption(String optionCode) {
            return super.setField(b -> b.paymentOption = PaymentOption.get(optionCode));
        }

        public Builder receiverName(String r) {
            return super.setField(b -> b.receiverName = r);
        }

        public Builder receiverPhone(String r) {
            return super.setField(b -> b.receiverPhone = r);
        }

        public Builder divyAddr(String d) {
            return super.setField(b -> b.divyAddr = d);
        }

        public Builder divyRequest(String d) {
            return super.setField(b -> b.divyRequest = d);
        }

        public Builder divyDate(Date d) {
            return super.setField(b -> b.divyDate = d);
        }

        public Builder transactionProductions(List<TransactionProduction> t) {
            return super.setField(b -> b.transactionProductions = t);
        }

        @Override
        public AddPurchaseRequestDTO build() {
            return new AddPurchaseRequestDTO(this);
        }
    }
}
