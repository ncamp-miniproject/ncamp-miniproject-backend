package com.model2.mvc.product.dto.response;

import com.model2.mvc.category.domain.Category;
import com.model2.mvc.common.ListData;
import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.dto.request.ListProductRequestDTO;

import java.util.List;

public class ListProductResponseDTO {
    private int count;
    private List<Product> products;
    private List<Category> categories;
    private Page pageInfo;
    private String menuMode;
    private Search searchInfo;
    private Integer currentCategoryNo;

    private ListProductResponseDTO() {
    }

    public static ListProductResponseDTO from(ListData<Product> listData,
                                              List<Category> categories,
                                              Page pageInfo,
                                              ListProductRequestDTO requestDTO,
                                              Search searchInfo) {
        ListProductResponseDTO dto = new ListProductResponseDTO();
        dto.count = listData.getCount();
        dto.products = listData.getList();
        dto.categories = categories;
        dto.pageInfo = pageInfo;
        dto.menuMode = requestDTO.getMenu();
        dto.searchInfo = searchInfo;
        dto.currentCategoryNo = requestDTO.getCategoryNo();
        return dto;
    }

    public int getCount() {
        return count;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Category> getCategories() {
        return categories;
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

    public Integer getCurrentCategoryNo() {
        return this.currentCategoryNo;
    }

    @Override
    public String toString() {
        return "ListProductResponseDTO{" +
               "count=" +
               count +
               ", products=" +
               products +
               ", categories=" +
               categories +
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
