package com.model2.mvc.product.controller;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.common.dto.Search;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.product.dto.request.ListProductRequestDTO;
import com.model2.mvc.product.dto.response.ListProductResponseDTO;
import com.model2.mvc.user.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListProductAction extends ProductAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = request.getParameter("page");
        String menuMode = request.getParameter("menu");

        System.out.println("searchCondition=" + request.getParameter("searchCondition"));
        System.out.println("searchKeyworkd=" + request.getParameter("searchKeyword"));

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if (menuMode == null || (menuMode.equals("manage") && !loginUser.getRole()
                                                                        .equals("admin"))) {
            return "redirect:/listProduct.do?menu=search";
        }

        int currentPage = Integer.parseInt(page == null ? "1" : page);
        Search searchVO = new Search();
        searchVO.setPage(currentPage);
        searchVO.setPageUnit(CommonConstants.PAGE_SIZE);
        searchVO.setSearchCondition(StringUtil.null2nullStr(request.getParameter("searchCondition")));
        searchVO.setSearchKeyword(StringUtil.null2nullStr(request.getParameter("searchKeyword")));

        ListProductRequestDTO requestDTO = new ListProductRequestDTO().builder()
                .page(currentPage)
                .menuMode(menuMode)
                .loginUser(loginUser)
                .search(searchVO)
                .build();

        ListProductResponseDTO result = super.productService.getProductList(requestDTO);

        request.setAttribute("data", result);
        return "forward:/product/listProduct.jsp";
    }
}
