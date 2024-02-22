package com.model2.mvc.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.user.service.UserService;
import com.model2.mvc.user.service.UserServiceImpl;

public class ListUserAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Search searchVO = new Search();

        int page = 1;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        searchVO.setPage(page);
        searchVO.setSearchCondition(request.getParameter("searchCondition"));
        searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
        searchVO.setPageUnit(CommonConstants.PAGE_SIZE);

        UserService service = new UserServiceImpl();
        Map<String, Object> map = service.getUserList(searchVO);

        request.setAttribute("total", map.get("count"));
        request.setAttribute("list", map.get("list"));
        request.setAttribute("searchVO", searchVO);
        request.setAttribute("currentPage", searchVO.getPage());
        
        int totalPage = 0;
        int total = (int)map.get("count");
        if (total > 0) {
            totalPage = total / searchVO.getPageUnit();
            if (total % searchVO.getPageUnit() > 0)
                totalPage += 1;
        }
        request.setAttribute("totalPage", totalPage);

        return "forward:/user/listUser.jsp";
    }
}