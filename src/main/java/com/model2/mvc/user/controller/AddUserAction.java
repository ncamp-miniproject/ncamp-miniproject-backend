package com.model2.mvc.user.controller;

import com.model2.mvc.framework.Action;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("addUserAction")
public class AddUserAction extends Action {
    private final UserService userService;

    @Autowired
    public AddUserAction(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User userVO = new User();
        userVO.setUserId(request.getParameter("userId"));
        userVO.setPassword(request.getParameter("password"));
        userVO.setUserName(request.getParameter("userName"));
        userVO.setSsn(request.getParameter("ssn"));

        userVO.setAddr(request.getParameter("addr"));
        userVO.setPhone(request.getParameter("phone"));
        userVO.setEmail(request.getParameter("email"));

        System.out.println(userVO);

        this.userService.addUser(userVO);

        return "redirect:/user/loginView.jsp";
    }
}