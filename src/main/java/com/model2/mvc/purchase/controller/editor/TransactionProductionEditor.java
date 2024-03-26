package com.model2.mvc.purchase.controller.editor;

import com.model2.mvc.product.domain.Product;
import com.model2.mvc.purchase.domain.TransactionProduction;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionProductionEditor extends PropertyEditorSupport {
    private static final TransactionProductionEditor singleton = new TransactionProductionEditor();

    private static final String MULTI_PARAM_DELIMITER = ",";
    private static final String QUERY_VALUE_DELIMITER = "%DFS";

    private TransactionProductionEditor() {
    }

    public static TransactionProductionEditor getInstance() {
        return singleton;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        List<TransactionProduction> tranProds = Arrays.stream(text.split(MULTI_PARAM_DELIMITER))
                .map(each -> each.split(QUERY_VALUE_DELIMITER))
                .map(each -> Arrays.stream(each).map(Integer::parseInt).collect(Collectors.toList()))
                .map(each -> new TransactionProduction(new Product(each.get(0)), each.get(1)))
                .collect(Collectors.toList());
        super.setValue(tranProds);
    }
}
