package com.model2.mvc.product.dto.request;

import com.model2.mvc.common.SearchCondition;

public class ListProductRequestDTO {
    private Integer page;
    private Integer pageSize;
    private String searchKeyword;
    private SearchCondition searchCondition;
    private String menu;
    private Integer categoryNo;

    public ListProductRequestDTO() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
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

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Integer getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(Integer categoryNo) {
        this.categoryNo = categoryNo;
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
