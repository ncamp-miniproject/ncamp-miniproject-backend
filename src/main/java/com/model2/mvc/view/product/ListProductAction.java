package com.model2.mvc.view.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.service.user.domain.User;

public class ListProductAction extends ProductAction {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws Exception {
        String page = request.getParameter("page");
        String menuMode = request.getParameter("menu");

        System.out.println("searchCondition=" +
                           request.getParameter("searchCondition"));
        System.out.println("searchKeyworkd=" +
                           request.getParameter("searchKeyword"));

        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("user");
        if (menuMode == null || (menuMode.equals("manage")
                && !loginUser.getRole().equals("admin"))) {
            return "/listProduct.do?menu=search";
        }

        int currentPage = Integer.parseInt(page == null ? "1" : page);
        Search searchVO = new Search();
        searchVO.setPage(currentPage);
        searchVO.setPageUnit(CommonConstants.PAGE_SIZE);
        searchVO.setSearchCondition(StringUtil
                .null2nullStr(request.getParameter("searchCondition")));
        searchVO.setSearchKeyword(StringUtil
                .null2nullStr(request.getParameter("searchKeyword")));

        Map<String, Object> resultMap = super.productService
                .getProductList(searchVO);

        request.setAttribute("menuMode", menuMode);
        request.setAttribute("pageInfo", resultMap.get("page"));
        request.setAttribute("count", resultMap.get("count"));
        request.setAttribute("productList", resultMap.get("productList"));
        request.setAttribute("searchInfo", searchVO);
        return "forward:/product/listProduct.jsp";
    }
}
