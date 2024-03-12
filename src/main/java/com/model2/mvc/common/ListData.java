package com.model2.mvc.common;

import java.util.List;

public class ListData<D> {
    private int count;
    private List<D> list;
    private int page;
    private int pageSize;

    public ListData() {
    }

    public ListData(int count, List<D> list) {
        this.count = count;
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<D> getList() {
        return list;
    }

    public void setList(List<D> list) {
        this.list = list;
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

    @Override
    public String toString() {
        return "ListData{" + "count=" + count + ", list=" + list + ", page=" + page + ", pageSize=" + pageSize + '}';
    }
}
