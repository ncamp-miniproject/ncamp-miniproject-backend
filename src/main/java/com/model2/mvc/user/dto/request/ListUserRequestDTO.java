package com.model2.mvc.user.dto.request;

public class ListUserRequestDTO {
    private int page;
    private int pageSize;
    private String searchCondition;
    private String searchKeyword;

    public ListUserRequestDTO(int page, int pageSize, String searchCondition, String searchKeyword) {
        this.page = page;
        this.pageSize = pageSize;
        this.searchCondition = searchCondition;
        this.searchKeyword = searchKeyword;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public String getSearchKeyword() {
        return searchKeyword;
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
