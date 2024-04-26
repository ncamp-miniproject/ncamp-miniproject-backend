package com.model2.mvc.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Deprecated
public class Search {
    private String searchCondition;
    private String searchKeyword;
    private int startRowNum;
    private int endRowNum;
}