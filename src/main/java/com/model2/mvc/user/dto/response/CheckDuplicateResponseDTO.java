package com.model2.mvc.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CheckDuplicateResponseDTO {
    private Boolean result;
    private String userId;
}
