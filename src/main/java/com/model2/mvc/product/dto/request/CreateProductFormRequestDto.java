package com.model2.mvc.product.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.model2.mvc.common.binder.jackson.LocalDateDeserializer;
import com.model2.mvc.common.binder.jackson.LocalDateSerializer;
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
    private MultipartFile imageFiles;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate manuDate;
    private int price;
    private String prodDetail;
    private String prodName;
    private int stock;
    private Integer categoryNo;
}
