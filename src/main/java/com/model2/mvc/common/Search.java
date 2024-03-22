package com.model2.mvc.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Search {
    String searchCondition;
    String searchKeyword;
    int endRowNum;
    private int startRowNum;
}