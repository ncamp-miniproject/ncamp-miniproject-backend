package com.model2.mvc.cart.service;

import com.model2.mvc.cart.dto.request.AddItemRequestDto;
import com.model2.mvc.cart.dto.request.NewItemRequestDto;
import com.model2.mvc.cart.dto.response.ListCartItemResponseDto;

public interface CartService {

    @Deprecated
    public String addItem(AddItemRequestDto requestDTO);

    public String addItem(NewItemRequestDto requestDto, String cartValue);

    public ListCartItemResponseDto getCartItemList(String cartValue);
}
