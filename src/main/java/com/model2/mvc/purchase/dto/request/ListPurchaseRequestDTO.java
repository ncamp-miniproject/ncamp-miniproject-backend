package com.model2.mvc.purchase.dto.request;

public class ListPurchaseRequestDTO {
    private int page;
    private int pageSize;
    private String searchKeyword;
    private String searchCondition;
    private String userId;

    public ListPurchaseRequestDTO(int page, int pageSize, String userId) {
        this.page = page;
        this.pageSize = pageSize;
        this.userId = userId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
