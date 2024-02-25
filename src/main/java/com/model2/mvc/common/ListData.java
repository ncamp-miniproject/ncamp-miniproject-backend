package com.model2.mvc.common;

import java.util.List;

public class ListData<D> {
    private int count;
    private List<D> list;

    public ListData(int count, List<D> list) {
        this.count = count;
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public List<D> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "ListData{" + "count=" + count + ", list=" + list + '}';
    }
}
