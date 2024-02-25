package com.model2.mvc.user.controller;

import com.model2.mvc.framework.Action;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.service.UserService;
import com.model2.mvc.user.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User userVO = new User().builder()
                                .userId(request.getParameter("userId"))
                                .password(request.getParameter("password"))
                                .build();

        UserService service = new UserServiceImpl();
        User dbVO = service.loginUser(userVO);

        HttpSession session = request.getSession();
        session.setAttribute("user", dbVO);

        return "redirect:/index.jsp";
    }
}