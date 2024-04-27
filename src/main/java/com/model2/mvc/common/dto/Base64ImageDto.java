package com.model2.mvc.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Base64ImageDto {
    private String fileExtension;
    private String base64Data;
}
