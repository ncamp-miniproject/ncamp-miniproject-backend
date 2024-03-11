package com.model2.mvc.common;

import java.util.Map;

public class Search {

    String searchCondition;
    String searchKeyword;
    int endRowNum;
    private int startRowNum;

    public Search() {
    }

    public static Search from(Map<String, Object> map) {
        Search search = new Search();
        search.searchCondition = (String)map.get("searchCondition");
        search.searchKeyword = (String)map.get("searchKeyword");
        return search;
    }

    public int getEndRowNum() {
        return endRowNum;
    }

    public void setEndRowNum(int endRowNum) {
        this.endRowNum = endRowNum;
    }

    public int getStartRowNum() {
        return startRowNum;
    }

    public void setStartRowNum(int startRowNum) {
        this.startRowNum = startRowNum;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
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
        return "Search{" +
               "searchCondition='" +
               searchCondition +
               '\'' +
               ", searchKeyword='" +
               searchKeyword +
               '\'' +
               ", endRowNum=" +
               endRowNum +
               ", startRowNum=" +
               startRowNum +
               '}';
    }
}