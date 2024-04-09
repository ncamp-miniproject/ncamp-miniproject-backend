package com.model2.mvc.purchase.dto.response;

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
public class ListPurchaseResponseDto {
    private Pagination paginationInfo;
    private int count;
    private List<GetPurchaseResponseDto> purchaseList;
    private String menu;

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
