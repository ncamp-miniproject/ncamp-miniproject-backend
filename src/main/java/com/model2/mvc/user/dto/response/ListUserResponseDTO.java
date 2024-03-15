package com.model2.mvc.user.dto.response;

import com.model2.mvc.common.Search;
import com.model2.mvc.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class ListUserResponseDTO {
    private int total;
    private List<User> list;
    private Search searchVO;
    private int currentPage;
    private int totalPage;
}
