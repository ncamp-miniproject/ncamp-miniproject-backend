package com.model2.mvc.user.controller;

import com.model2.mvc.framework.Action;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component("loginAction")
public class LoginAction extends Action {
    private final UserService userService;

    @Autowired
    public LoginAction(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User userVO = new User();
        userVO.setUserId(request.getParameter("userId"));
        userVO.setPassword(request.getParameter("password"));

        User dbVO = this.userService.loginUser(userVO);

        HttpSession session = request.getSession();
        session.setAttribute("user", dbVO);

        return "redirect:/index.jsp";
    }
}