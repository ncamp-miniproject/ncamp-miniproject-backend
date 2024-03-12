package com.model2.mvc.cart.service;

import com.model2.mvc.cart.dto.request.AddItemRequestDTO;
import com.model2.mvc.cart.dto.response.ListCartItemResponseDTO;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private final ProductRepository productRepository;

    @Value("#{constantProperties['defaultPageSize']}")
    private int defaultPageSize;

    @Value("#{constantProperties['defaultPageDisplay']}")
    private int defaultPageDisplay;

    @Value("#{constantProperties['queryValueDelimiter']}")
    private String queryValueDelimiter;

    @Value("#{constantProperties['cookieItemDelimiter']}")
    private String cookieItemDelimiter;

    @Value("#{constantProperties['cookieKeyValueDelimiter']}")
    private String cookieKeyValueDelimiter;

    @Autowired
    public CartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String addItem(AddItemRequestDTO requestDTO) {
        if (Integer.parseInt(requestDTO.getQuantity()) <= 0) {
            // TODO: do more things
            throw new IllegalArgumentException();
        }
        String cartValue = requestDTO.getCartValue();
        if (cartValue.isEmpty()) {
            return requestDTO.getProdNo() + cookieKeyValueDelimiter + requestDTO.getQuantity();
        } else {
            Map<String, String> map = new HashMap<>();
            Arrays.stream(cartValue.split(cookieItemDelimiter)).forEach(v -> {
                String[] parsed = v.split(cookieKeyValueDelimiter);
                String prodNo = parsed[0];
                String quantity = parsed[1];
                map.put(prodNo, quantity);
            });

            map.put(requestDTO.getProdNo(), requestDTO.getQuantity());
            return String.join(cookieItemDelimiter,
                               map.keySet()
                                       .stream()
                                       .map(k -> k + cookieKeyValueDelimiter + map.get(k))
                                       .collect(Collectors.toList()));
        }
    }

    @Override
    public ListCartItemResponseDTO getCartItemList(String cartValue) {
        if (cartValue == null || cartValue.isEmpty()) {
            return new ListCartItemResponseDTO(0, 0, new HashMap<>());
        }

        Map<Integer, Integer> parsed = new HashMap<>();

        Arrays.stream(cartValue.split(cookieItemDelimiter))
                .map(v -> v.split(cookieKeyValueDelimiter))
                .map(d -> new int[] { Integer.parseInt(d[0]), Integer.parseInt(d[1]) })
                .forEach(i -> parsed.put(i[0], i[1]));

        List<Integer> keyList = parsed.keySet().stream().collect(Collectors.toList());
        Map<Integer, Product> queryResult = productRepository.findProductsByIds(keyList);

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
