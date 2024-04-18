package com.model2.mvc.user.dto.response;

import com.model2.mvc.common.Pagination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class ListUserResponseDto {
    private int count;
    private List<UserResponseDto> list;
    private Pagination paginationInfo;
}
