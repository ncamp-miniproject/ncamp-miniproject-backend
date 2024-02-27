package com.model2.mvc.purchase.controller;

import com.model2.mvc.purchase.dto.request.AddPurchaseViewResponseDTO;
import com.model2.mvc.user.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AddPurchaseViewAction extends PurchaseAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<Integer, Integer> prodNoQuantityMap = new HashMap<>();

        Arrays.stream(request.getParameterValues("purchase"))
                .map(p -> p.split("-"))
                .forEach(m -> prodNoQuantityMap.put(Integer.parseInt(m[0]), Integer.parseInt(m[1])));
        AddPurchaseViewResponseDTO responseDTO = super.purchaseService.getProductsWithQuantity(prodNoQuantityMap);

        responseDTO.setPurchaseUser((User)request.getSession().getAttribute("user"));

        System.out.println("response data: " + responseDTO);

        request.setAttribute("data", responseDTO);
        request.setAttribute("loginUser", request.getSession().getAttribute("user"));
        return "forward:/purchase/addPurchaseView.jsp";
    }
}
