package com.model2.mvc.user.dto.request;

import com.model2.mvc.common.SearchCondition;

public class ListUserRequestDTO {
    private int page;
    private int pageSize;
    private SearchCondition searchCondition;
    private String searchKeyword;

    public ListUserRequestDTO() {
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

    public SearchCondition getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(SearchCondition searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    @Override
    public String toString() {
        return "ListUserRequestDTO{" +
               "page=" +
               page +
               ", pageSize=" +
               pageSize +
               ", searchCondition='" +
               searchCondition +
               '\'' +
               ", searchKeyword='" +
               searchKeyword +
               '\'' +
               '}';
    }
}
