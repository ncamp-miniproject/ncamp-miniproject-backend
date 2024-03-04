package com.model2.mvc.user.controller;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.common.dto.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ListUserAction extends Action {
    private UserService userService;

    public ListUserAction(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Search searchVO = new Search();

        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        searchVO.setStartRowNum(page);
        searchVO.setSearchCondition(request.getParameter("searchCondition"));
        searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
        searchVO.setEndRowNum(CommonConstants.PAGE_SIZE);

        Map<String, Object> map = this.userService.getUserList(searchVO);

        request.setAttribute("total", map.get("count"));
        request.setAttribute("list", map.get("list"));
        request.setAttribute("searchVO", searchVO);
        request.setAttribute("currentPage", searchVO.getStartRowNum());

        int totalPage = 0;
        int total = (int)map.get("count");
        if (total > 0) {
            totalPage = total / searchVO.getEndRowNum();
            if (total % searchVO.getEndRowNum() > 0) {
                totalPage += 1;
            }
        }
        request.setAttribute("totalPage", totalPage);

        return "forward:/user/listUser.jsp";
    }
}