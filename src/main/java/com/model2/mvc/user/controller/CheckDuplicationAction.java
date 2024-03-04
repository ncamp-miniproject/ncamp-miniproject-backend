package com.model2.mvc.user.controller;

import com.model2.mvc.framework.Action;
import com.model2.mvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("checkDuplicationAction")
public class CheckDuplicationAction extends Action {
    private final UserService userService;

    @Autowired
    public CheckDuplicationAction(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");

        boolean result = userService.checkDuplication(userId);

        request.setAttribute("result", result);
        request.setAttribute("userId", userId);

        return "forward:/user/checkDuplication.jsp";
    }
}