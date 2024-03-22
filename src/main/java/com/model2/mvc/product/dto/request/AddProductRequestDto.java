package com.model2.mvc.product.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AddProductRequestDto {
    private MultipartFile imageFile;
    private String manuDate;
    private int price;
    private String prodDetail;
    private String prodName;
    private int stock;
    private Integer categoryNo;
}
