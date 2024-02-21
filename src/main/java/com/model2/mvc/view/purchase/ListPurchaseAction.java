package com.model2.mvc.view.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.service.user.domain.User;

public class ListPurchaseAction extends PurchaseAction {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("user");

        Search searchInfo = new Search();
        String page = request.getParameter("page");

        int pageNum = page == null ? 1 : Integer.parseInt(page);
        searchInfo.setPage(pageNum);
        searchInfo.setPageUnit(CommonConstants.PAGE_SIZE);
        searchInfo.setSearchCondition(StringUtil
                .null2nullStr(request.getParameter("searchCondition")));
        searchInfo.setSearchKeyword(StringUtil
                .null2nullStr(request.getParameter("searchKeyword")));
        Map<String, Object> resultMap = super.purchaseService
                .getPurchaseList(searchInfo, loginUser.getUserId());

        System.out.println(resultMap);
        
        request.setAttribute("pageInfo", resultMap.get("page"));
        request.setAttribute("count", resultMap.get("count"));
        request.setAttribute("purchaseList", resultMap.get("purchaseList"));
        request.setAttribute("loginUser", loginUser);
        return "forward:/purchase/listPurchase.jsp"; // TODO: forward list
    }
}
