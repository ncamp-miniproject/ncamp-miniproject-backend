package com.model2.mvc.user.controller;

import com.model2.mvc.framework.Action;
import com.model2.mvc.user.dto.request.ListUserRequestDTO;
import com.model2.mvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("listUserAction")
public class ListUserAction extends Action {
    private UserService userService;

    @Value("#{constantProperties['defaultPageSize']}")
    private int defaultPageSize;

    @Autowired
    public ListUserAction(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        ListUserRequestDTO requestDTO = new ListUserRequestDTO(page,
                                                               defaultPageSize,
                                                               request.getParameter("searchCondition"),
                                                               request.getParameter("searchKeyword"));

        Map<String, Object> map = this.userService.getUserList(requestDTO);

        request.setAttribute("total", map.get("count"));
        request.setAttribute("list", map.get("list"));
        request.setAttribute("searchVO", map.get("searchVO"));
        request.setAttribute("currentPage", page);

        int totalPage = 0;
        int total = (int)map.get("count");
        if (total > 0) {
            totalPage = total / requestDTO.getPageSize();
            if (total % requestDTO.getPageSize() > 0) {
                totalPage += 1;
            }
        }
        request.setAttribute("totalPage", totalPage);

        return "forward:/user/listUser.jsp";
    }
}