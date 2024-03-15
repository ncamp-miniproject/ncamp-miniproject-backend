package com.model2.mvc.user.dto.request;

import com.model2.mvc.common.SearchCondition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ListUserRequestDTO {
    private int page;
    private int pageSize;
    private SearchCondition searchCondition;
    private String searchKeyword;
}
