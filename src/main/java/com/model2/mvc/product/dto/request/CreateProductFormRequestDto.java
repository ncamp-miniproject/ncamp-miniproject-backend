package com.model2.mvc.product.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateProductFormRequestDto {
    private MultipartFile imageFile;
    private LocalDate manuDate;
    private int price;
    private String prodDetail;
    private String prodName;
    private int stock;
    private Integer categoryNo;
}
