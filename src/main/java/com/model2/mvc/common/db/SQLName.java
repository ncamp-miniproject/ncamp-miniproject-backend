package com.model2.mvc.common.db;

public enum SQLName {
    FIND_PRODUCT("findproduct"),
    FIND_PRODUCT_LIST_BY_PROD_NAME("findproductlistbyprodname"),
    INSERT_PRODUCT("insertproduct"),
    UPDATE_PRODUCT("updateproduct"),

    FIND_PURCHASE("findpurchase"),
    FIND_PRODUCTS_BY_IDS("findproductbyids"),
    GET_ALL_PURCHASE_LIST("getallpurchaselist"),
    GET_PURCHASE_LIST_BY_USER_ID("getpurchaselistbyuserid"),
    INSERT_PURCHASE("insertpurchase"),
    UPDATE_PURCHASE("updatepurchase"),
    UPDATE_TRAN_CODE("updatetrancode"),
    INSERT_TRAN_PRO("inserttranpro");

    private String name;

    SQLName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
