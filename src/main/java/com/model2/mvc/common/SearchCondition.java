package com.model2.mvc.common;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum SearchCondition {
    NO_CONDITION("-1"),
    BY_ID("0"),
    BY_NAME("1"),
    BY_INTEGER_RANGE("2"),
    BY_JOIN_KEY("3");

    private final String conditionCode;

    SearchCondition(String conditionCode) {
        this.conditionCode = conditionCode;
    }

    public String getConditionCode() {
        return this.conditionCode;
    }

    private static final Map<String, SearchCondition> codeTable = new HashMap<>();

    static {
        EnumSet<SearchCondition> enumSet = EnumSet.allOf(SearchCondition.class);
        enumSet.forEach(sc -> codeTable.put(sc.getConditionCode(), sc));
    }

    public static Optional<SearchCondition> getSearchCondition(String conditionCode) {
        return Optional.ofNullable(codeTable.get(conditionCode));
    }
}
