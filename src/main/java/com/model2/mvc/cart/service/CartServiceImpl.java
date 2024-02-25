package com.model2.mvc.cart.service;

import com.model2.mvc.cart.dto.request.AddItemRequestDTO;
import com.model2.mvc.cart.dto.response.AddItemResponseDTO;
import com.model2.mvc.common.CommonConstants;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CartServiceImpl implements CartService {
    private static CartService instance = new CartServiceImpl();


    public static CartService getInstance() {
        return instance;
    }

    @Override
    public AddItemResponseDTO addItem(AddItemRequestDTO requestDTO) {
        Cookie cookie = requestDTO.getCookie();
        String value = cookie.getValue();
        Map<String, String> map = new HashMap<>();
        Arrays.stream(value.split(CommonConstants.COOKIE_DELIMITER))
              .forEach(v -> {
                  String[] parsed = v.split(CommonConstants.COOKIE_KEY_VALUE_DELIMITER);
                  String prodNo = parsed[0];
                  String quantity = parsed[1];
                  map.put(prodNo, quantity);
              });

        map.put(requestDTO.getProdNo(), requestDTO.getQuantity());
        Cookie newCookie = new Cookie("cart",
                                      String.join(CommonConstants.COOKIE_DELIMITER,
                                                  map.keySet()
                                                     .stream()
                                                     .map(k -> k + CommonConstants.COOKIE_KEY_VALUE_DELIMITER +
                                                               map.get(k))
                                                     .toList()));
        return new AddItemResponseDTO(newCookie);
    }
}
