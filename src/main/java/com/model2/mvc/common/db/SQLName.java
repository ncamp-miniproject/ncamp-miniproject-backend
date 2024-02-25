package com.model2.mvc.common.db;

public enum SQLName {
    FIND_PRODUCT("findproduct"),
    FIND_PRODUCT_LIST_BY_PROD_NAME("findproductlistbyprodname"),
    INSERT_PRODUCT("insertproduct"),
    UPDATE_PRODUCT("updateproduct"),

    FIND_PURCHASE("findpurchase"),
    GET_PURCHASE_LIST("getpurchaselist"),
    GET_SALE_LIST("getsalelist"),
    INSERT_PURCHASE("insertpurchase"),
    UPDATE_PURCHASE("updatepurchase"),
    UPDATE_TRAN_CODE("updatetrancode");

    private String name;

    SQLName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
