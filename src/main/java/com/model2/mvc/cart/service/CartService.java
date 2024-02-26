package com.model2.mvc.cart.service;

import com.model2.mvc.cart.dto.request.AddItemRequestDTO;
import com.model2.mvc.cart.dto.response.AddItemResponseDTO;
import com.model2.mvc.cart.dto.response.ListCartItemResponseDTO;

public interface CartService {

    public static CartService getInstance() {
        return CartServiceImpl.getInstance();
    }

    public AddItemResponseDTO addItem(AddItemRequestDTO requestDTO);

    public ListCartItemResponseDTO getCartItemList(String cartValue);
}
