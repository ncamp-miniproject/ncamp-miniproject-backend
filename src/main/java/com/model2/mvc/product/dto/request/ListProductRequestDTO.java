package com.model2.mvc.product.dto.request;

public class ListProductRequestDTO {
    private int page;
    private int pageSize;
    private String searchKeyword;
    private String searchCondition;
    private String menu;

    public ListProductRequestDTO() {
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

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "ListProductRequestDTO{" +
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
               ", menu='" +
               menu +
               '\'' +
               '}';
    }
}
