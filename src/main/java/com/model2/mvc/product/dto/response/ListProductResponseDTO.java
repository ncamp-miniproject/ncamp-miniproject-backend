package com.model2.mvc.product.dto.response;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.dto.Page;
import com.model2.mvc.common.dto.Search;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.dto.request.ListProductRequestDTO;

import java.util.List;

public class ListProductResponseDTO {
    private int count;
    private List<Product> products;
    private Page pageInfo;
    private String menuMode;
    private Search searchInfo;

    private ListProductResponseDTO() {
    }

    public static ListProductResponseDTO from(ListData<Product> listData,
                                              Page pageInfo,
                                              ListProductRequestDTO requestDTO) {
        ListProductResponseDTO dto = new ListProductResponseDTO();
        dto.count = listData.getCount();
        dto.products = listData.getList();
        dto.pageInfo = pageInfo;
        dto.menuMode = requestDTO.getMenuMode();
        dto.searchInfo = requestDTO.getSearch();
        return dto;
    }

    public int getCount() {
        return count;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Page getPageInfo() {
        return pageInfo;
    }

    public String getMenuMode() {
        return menuMode;
    }

    public Search getSearchInfo() {
        return searchInfo;
    }

    @Override
    public String toString() {
        return "ListProductResponseDTO{" +
               "count=" +
               count +
               ", products=" +
               products +
               ", pageInfo=" +
               pageInfo +
               ", menuMode='" +
               menuMode +
               '\'' +
               ", searchInfo=" +
               searchInfo +
               '}';
    }
}
