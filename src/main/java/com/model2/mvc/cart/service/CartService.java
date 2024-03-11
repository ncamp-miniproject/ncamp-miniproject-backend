package com.model2.mvc.cart.service;

import com.model2.mvc.cart.dto.request.AddItemRequestDTO;
import com.model2.mvc.cart.dto.response.ListCartItemResponseDTO;

public interface CartService {

    public String addItem(AddItemRequestDTO requestDTO);

    public ListCartItemResponseDTO getCartItemList(String cartValue);
}
