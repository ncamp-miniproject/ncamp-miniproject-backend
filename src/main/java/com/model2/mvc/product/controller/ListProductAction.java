package com.model2.mvc.product.controller;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.product.dto.request.ListProductRequestDTO;
import com.model2.mvc.product.dto.response.ListProductResponseDTO;
import com.model2.mvc.product.service.ProductService;
import com.model2.mvc.user.domain.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component("listProductAction")
public class ListProductAction extends Action {
    private final ProductService productService;

    public ListProductAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = request.getParameter("page");
        String menuMode = request.getParameter("menu");

        System.out.println("searchCondition=" + request.getParameter("searchCondition"));
        System.out.println("searchKeyword=" + request.getParameter("searchKeyword"));

        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("user");
        if (menuMode == null || (menuMode.equals("manage") && !loginUser.getRole().equals("admin"))) {
            return "redirect:/listProduct.do?menu=search";
        }

        int currentPage = Integer.parseInt(page == null ? "1" : page);

        ListProductRequestDTO requestDTO = new ListProductRequestDTO().builder()
                .page(currentPage)
                .pageSize(CommonConstants.PAGE_SIZE)
                .searchCondition(StringUtil.null2nullStr(request.getParameter("searchCondition")))
                .searchKeyword(StringUtil.null2nullStr(request.getParameter("searchKeyword")))
                .menuMode(menuMode)
                .loginUser(loginUser)
                .build();

        ListProductResponseDTO result = this.productService.getProductList(requestDTO);

        request.setAttribute("data", result);
        return "forward:/product/listProduct.jsp";
    }
}
