package com.model2.mvc.product.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum OrderBy {
    PROD_NO("prodNo", "prod_no"),
    PROD_NAME("prodName", "prod_name"),
    PRICE("price", "price"),
    REG_DATE("regDate", "reg_date");

    private final String propertyName;
    private final String columnName;

    private static final Map<String, OrderBy> PROPERTY_INDEX;
    private static final Map<String, OrderBy> COLUMN_INDEX;

    static {
        PROPERTY_INDEX = new HashMap<>();
        for (OrderBy orderBy : OrderBy.values()) {
            PROPERTY_INDEX.put(orderBy.getPropertyName(), orderBy);
        }

        COLUMN_INDEX = new HashMap<>();
        for (OrderBy orderBy : OrderBy.values()) {
            COLUMN_INDEX.put(orderBy.getColumnName(), orderBy);
        }
    }

    public static Optional<OrderBy> findByPropertyName(String propertyName) {
        return Optional.ofNullable(PROPERTY_INDEX.get(propertyName.trim()));
    }

    public static Optional<OrderBy> findByColumnName(String columnName) {
        return Optional.ofNullable(COLUMN_INDEX.get(columnName.trim()));
    }

    @Override
    public String toString() {
        return this.propertyName;
    }
}
