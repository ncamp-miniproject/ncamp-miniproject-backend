package com.model2.mvc.purchase.util;

import com.model2.mvc.product.domain.Product;
import com.model2.mvc.purchase.domain.TransactionProduction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class TransactionProductionEditor extends PropertyEditorSupport {

    @Value("#{constantProperties['queryValueDelimiter']}")
    private String queryValueDelimiter;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] prodQuantityPair = text.split(queryValueDelimiter);
        super.setValue(new TransactionProduction(new Product(Integer.parseInt(prodQuantityPair[0])),
                                                 Integer.parseInt(prodQuantityPair[1])));
    }
}
