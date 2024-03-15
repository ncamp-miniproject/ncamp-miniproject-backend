package com.model2.mvc.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ListData<D> {
    private int count;
    private List<D> list;
    private int page;
    private int pageSize;
    private SearchCondition searchCondition;

    public ListData(int count, List<D> list) {
        this.count = count;
        this.list = list;
    }
}
