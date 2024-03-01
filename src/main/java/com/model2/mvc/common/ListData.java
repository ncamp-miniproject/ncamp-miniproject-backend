package com.model2.mvc.common;

import java.util.List;

public class ListData<D> {
    private int count;
    private List<D> list;

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

    @Override
    public String toString() {
        return "ListData{" + "count=" + count + ", list=" + list + '}';
    }
}
