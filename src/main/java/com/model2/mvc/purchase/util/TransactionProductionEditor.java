package com.model2.mvc.purchase.util;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.purchase.domain.TransactionProduction;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class TransactionProductionEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] prodQuantityPair = text.split(CommonConstants.QUERY_VALUE_DELIMITER);
        super.setValue(new TransactionProduction(new Product(Integer.parseInt(prodQuantityPair[0])),
                                                 Integer.parseInt(prodQuantityPair[1])));
    }
}
