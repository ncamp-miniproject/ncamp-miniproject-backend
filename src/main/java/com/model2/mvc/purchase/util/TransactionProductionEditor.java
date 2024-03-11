package com.model2.mvc.purchase.util;

import com.model2.mvc.product.domain.Product;
import com.model2.mvc.purchase.domain.TransactionProduction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionProductionEditor extends PropertyEditorSupport {

    private static final String MULTI_PARAM_DELIMITER = ",";

    @Value("#{constantProperties['queryValueDelimiter']}")
    private String queryValueDelimiter;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        System.out.println("TranProdEditor: " + text);
        List<TransactionProduction> tranProds = Arrays.stream(text.split(MULTI_PARAM_DELIMITER))
                .map(each -> each.split(queryValueDelimiter))
                .map(each -> new int[] { Integer.parseInt(each[0]), Integer.parseInt(each[1]) })
                .map(each -> new TransactionProduction(new Product(each[0]), each[1]))
                .collect(Collectors.toList());
        super.setValue(tranProds);
    }
}
