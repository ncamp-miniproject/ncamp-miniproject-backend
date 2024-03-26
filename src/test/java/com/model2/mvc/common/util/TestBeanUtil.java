package com.model2.mvc.common.util;

import static org.assertj.core.api.Assertions.*;

import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.dto.request.CreateProductRequestDto;
import org.junit.Test;

import java.time.LocalDate;

public class TestBeanUtil {

    @Test
    public void generateGiven() {
        CreateProductRequestDto from = CreateProductRequestDto.builder()
                .prodName("sample-name")
                .prodDetail("sample-detail")
                .price(1000)
                .manuDate(LocalDate.now())
                .stock(100)
                .imageName("image.jpg")
                .base64ImageData("base64:sdf9hqfioesav3aF2fajpfe")
                .categoryNo(1000)
                .build();
        try {
            Product generated = BeanUtil.generateGiven(Product.class, from);

            assertThat(generated.getProdName()).isEqualTo(from.getProdName());
            assertThat(generated.getProdDetail()).isEqualTo(from.getProdDetail());
            assertThat(generated.getManuDate()).isEqualTo(from.getManuDate());
            assertThat(generated.getPrice()).isEqualTo(from.getPrice());
            assertThat(generated.getStock()).isEqualTo(from.getStock());

            assertThat(generated.getRegDate()).isNull();
            assertThat(generated.getFileName()).isNull();
            assertThat(generated.getProdNo()).isEqualTo(0);
            assertThat(generated.getCategory()).isNull();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}