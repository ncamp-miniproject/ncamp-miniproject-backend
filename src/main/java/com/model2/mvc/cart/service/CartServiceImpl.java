package com.model2.mvc.cart.service;

import com.model2.mvc.cart.dto.request.AddItemRequestDTO;
import com.model2.mvc.cart.dto.response.AddItemResponseDTO;
import com.model2.mvc.cart.dto.response.ListCartItemResponseDTO;
import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.product.dao.ProductDAO;
import com.model2.mvc.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private final ProductDAO productDAO;

    @Autowired
    public CartServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public AddItemResponseDTO addItem(AddItemRequestDTO requestDTO) {
        if (Integer.parseInt(requestDTO.getQuantity()) <= 0) {
            // TODO: do more things
            throw new IllegalArgumentException();
        }
        Cookie cookie = requestDTO.getCookie();
        String value = cookie.getValue();
        Cookie newCookie;
        if (value.isEmpty()) {
            newCookie = new Cookie("cart",
                                   requestDTO.getProdNo() +
                                   CommonConstants.COOKIE_KEY_VALUE_DELIMITER +
                                   requestDTO.getQuantity());
        } else {
            Map<String, String> map = new HashMap<>();
            Arrays.stream(value.split(CommonConstants.COOKIE_DELIMITER)).forEach(v -> {
                String[] parsed = v.split(CommonConstants.COOKIE_KEY_VALUE_DELIMITER);
                String prodNo = parsed[0];
                String quantity = parsed[1];
                map.put(prodNo, quantity);
            });

            map.put(requestDTO.getProdNo(), requestDTO.getQuantity());
            newCookie = new Cookie("cart",
                                   String.join(CommonConstants.COOKIE_DELIMITER,
                                               map.keySet()
                                                       .stream()
                                                       .map(k -> k +
                                                                 CommonConstants.COOKIE_KEY_VALUE_DELIMITER +
                                                                 map.get(k))
                                                       .collect(Collectors.toList())));
        }
        return new AddItemResponseDTO(newCookie);
    }

    @Override
    public ListCartItemResponseDTO getCartItemList(String cartValue) {
        if (cartValue.isEmpty()) {
            return new ListCartItemResponseDTO(0, 0, new HashMap<>());
        }

        Map<Integer, Integer> parsed = new HashMap<>();

        Arrays.stream(cartValue.split(CommonConstants.COOKIE_DELIMITER))
                .map(v -> v.split(CommonConstants.COOKIE_KEY_VALUE_DELIMITER))
                .map(d -> new int[] { Integer.parseInt(d[0]), Integer.parseInt(d[1]) })
                .forEach(i -> parsed.put(i[0], i[1]));

        List<Integer> keyList = parsed.keySet().stream().collect(Collectors.toList());
        Map<Integer, Product> queryResult = productDAO.findProductsByIds(keyList);

        Map<Product, Integer> resultMap = new HashMap<>();
        for (Integer key : keyList) {
            resultMap.put(queryResult.get(key), parsed.get(key));
        }

        int priceSum = resultMap.keySet()
                .stream()
                .reduce(0, (i, p) -> i + p.getPrice() * resultMap.get(p), Integer::sum);
        int itemCount = keyList.size();
        return new ListCartItemResponseDTO(priceSum, itemCount, resultMap);
    }
}
