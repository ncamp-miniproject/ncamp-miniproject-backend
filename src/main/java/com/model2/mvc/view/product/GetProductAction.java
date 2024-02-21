package com.model2.mvc.view.product;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.domain.Product;
import com.model2.mvc.service.user.domain.User;

public class GetProductAction extends Action {
    private ProductService productService;

    public GetProductAction() {
        this.productService = ProductService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws Exception {
        int prodNo = Integer.parseInt(request.getParameter("prodNo"));
        if (request.getParameter("menu").equals("manage")) {
            return "redirect:/updateProductView.do?prodNo=" + prodNo;
        }

        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieOptional = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("history"))
                .findAny();
        Cookie cookie = cookieOptional.orElse(new Cookie("history", ""));
        cookie.setValue(cookie.getValue().isEmpty() ? String.valueOf(prodNo)
                : StringUtil.addValueWithoutDuplicate(cookie.getValue(),
                                                      String.valueOf(prodNo),
                                                      "-"));
        response.addCookie(cookie);

        Product result = this.productService.getProduct(prodNo);

        request.setAttribute("productData", result);

        HttpSession session = request.getSession();
        if (!session.isNew() && session.getAttribute("user") != null) {
            User loginUser = (User)session.getAttribute("user");
            request.setAttribute("purchaseable",
                                 loginUser.getRole().equals("user"));
        } else {
            request.setAttribute("purchaseable", false);
        }

        return "forward:/product/getProduct.jsp";
    }
}
