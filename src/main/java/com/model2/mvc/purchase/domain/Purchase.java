package com.model2.mvc.purchase.domain;

import com.model2.mvc.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
public class Purchase {
    private Integer tranNo;
    private User buyer;
    private PaymentOption paymentOption;
    private String receiverName;
    private String receiverPhone;
    private String divyAddr;
    private String divyRequest;
    private TranStatusCode tranStatusCode;
    private LocalDate orderDate;
    private LocalDate divyDate;
    private List<TransactionProduction> transactionProductions;

    public Purchase() {
        this.transactionProductions = new ArrayList<>();
    }

    public Purchase(Integer tranNo) {
        this();
        this.tranNo = tranNo;
    }

    public void addTransactionProducts(TransactionProduction transactionProduction) {
        this.transactionProductions.add(transactionProduction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Purchase purchase = (Purchase)o;
        return Objects.equals(tranNo, purchase.tranNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tranNo);
    }
}