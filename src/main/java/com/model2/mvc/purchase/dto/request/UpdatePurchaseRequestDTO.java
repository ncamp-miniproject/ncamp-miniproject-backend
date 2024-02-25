package com.model2.mvc.purchase.dto.request;

import com.model2.mvc.common.Buildable;
import com.model2.mvc.common.BuilderTemplate;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.purchase.domain.PaymentOption;

import java.sql.Date;

public class UpdatePurchaseRequestDTO implements Buildable<UpdatePurchaseRequestDTO.Builder> {
    private int tranNo;
    private String buyerId;
    private PaymentOption paymentOption;
    private String receiverName;
    private String receiverPhone;
    private String divyAddr;
    private String divyRequest;
    private Date divyDate;

    private UpdatePurchaseRequestDTO(Builder from) {
        this.tranNo = from.tranNo;
        this.buyerId = from.buyerId;
        this.paymentOption = from.paymentOption;
        this.receiverName = from.receiverName;
        this.receiverPhone = from.receiverPhone;
        this.divyAddr = from.divyAddr;
        this.divyRequest = from.divyRequest;
        this.divyDate = from.divyDate;
    }

    public UpdatePurchaseRequestDTO() {
    }

    @Override
    public Builder builder() {
        return new Builder(this);
    }

    public int getTranNo() {
        return tranNo;
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

    public static class Builder extends BuilderTemplate<UpdatePurchaseRequestDTO> {
        private int tranNo;
        private String buyerId;
        private PaymentOption paymentOption;
        private String receiverName;
        private String receiverPhone;
        private String divyAddr;
        private String divyRequest;
        private Date divyDate;

        private Builder(UpdatePurchaseRequestDTO from) {
            this.tranNo = from.tranNo;
            this.buyerId = from.buyerId;
            this.paymentOption = from.paymentOption;
            this.receiverName = from.receiverName;
            this.receiverPhone = from.receiverPhone;
            this.divyAddr = from.divyAddr;
            this.divyRequest = from.divyRequest;
            this.divyDate = from.divyDate;
        }

        public Builder tranNo(int t) {
            return super.setField(b -> b.tranNo = t);
        }

        public Builder buyerId(String u) {
            return super.setField(b -> b.buyerId = u);
        }

        public Builder paymentOption(String p) {
            return super.setField(b -> b.paymentOption = PaymentOption.get(p));
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

        public Builder divyDate(String d) {
            return super.setField(b -> b.divyDate = StringUtil.parseDate(d, "-"));
        }

        @Override
        public UpdatePurchaseRequestDTO build() {
            return new UpdatePurchaseRequestDTO(this);
        }
    }
}
