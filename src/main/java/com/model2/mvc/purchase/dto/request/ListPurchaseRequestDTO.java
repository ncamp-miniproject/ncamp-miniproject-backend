package com.model2.mvc.purchase.dto.request;

import com.model2.mvc.common.SearchCondition;

public class ListPurchaseRequestDTO {
    private int page;
    private int pageSize;
    private String searchKeyword;
    private SearchCondition searchCondition;

    public ListPurchaseRequestDTO() {
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

    public SearchCondition getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(SearchCondition searchCondition) {
        this.searchCondition = searchCondition;
    }

    @Override
    public String toString() {
        return "ListPurchaseRequestDTO{" +
               "page=" +
               page +
               ", pageSize=" +
               pageSize +
               ", searchKeyword='" +
               searchKeyword +
               '\'' +
               ", searchCondition='" +
               searchCondition +
               '\'' +
               '}';
    }
}
