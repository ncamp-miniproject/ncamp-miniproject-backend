package com.model2.mvc.cart.service;

import com.model2.mvc.cart.dto.request.AddItemRequestDto;
import com.model2.mvc.cart.dto.response.ListCartItemResponseDto;

public interface CartService {

    public String addItem(AddItemRequestDto requestDTO);

    public ListCartItemResponseDto getCartItemList(String cartValue);
}
