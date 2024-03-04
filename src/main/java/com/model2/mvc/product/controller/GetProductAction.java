package com.model2.mvc.product.controller;

import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.product.dto.response.GetProductResponseDTO;
import com.model2.mvc.product.service.ProductService;
import com.model2.mvc.user.domain.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Optional;

public class GetProductAction extends Action {
    private ProductService productService;

    public GetProductAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int prodNo = Integer.parseInt(request.getParameter("prodNo"));
        String menu = request.getParameter("menu");
        if (menu.equals("manage")) {
            return "redirect:/updateProductView.do?prodNo=" + prodNo;
        }

        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieOptional = Arrays.stream(cookies).filter(c -> c.getName().equals("history")).findAny();
        Cookie cookie = cookieOptional.orElse(new Cookie("history", ""));
        cookie.setValue(cookie.getValue().isEmpty()
                        ? String.valueOf(prodNo)
                        : StringUtil.addValueWithoutDuplicate(cookie.getValue(), String.valueOf(prodNo), "-"));
        response.addCookie(cookie);

        GetProductResponseDTO result = this.productService.getProduct(prodNo);


        HttpSession session = request.getSession();
        if (!session.isNew() && session.getAttribute("user") != null) {
            User loginUser = (User)session.getAttribute("user");
            result.setPurchasable(loginUser.getRole().equals("user") && result.getStock() > 0);
            System.out.println(loginUser);
        } else {
            result.setPurchasable(false);
        }

        System.out.println(result);

        request.setAttribute("productData", result);
        return "forward:/product/getProduct.jsp";
    }
}
