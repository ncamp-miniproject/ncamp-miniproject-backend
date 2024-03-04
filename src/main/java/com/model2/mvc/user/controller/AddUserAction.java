package com.model2.mvc.user.controller;

import com.model2.mvc.framework.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUserAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        User userVO = new User().builder()
//                .userId(request.getParameter("userId"))
//                .password(request.getParameter("password"))
//                .userName(request.getParameter("userName"))
//                .ssn(request.getParameter("ssn"))
//
//                .addr(request.getParameter("addr"))
//                .phone(request.getParameter("phone"))
//                .email(request.getParameter("email"))
//                .build();
//
//        System.out.println(userVO);
//
//        UserService service = new UserServiceImpl();
//        service.addUser(userVO);
//
//        return "redirect:/user/loginView.jsp";
        throw new UnsupportedOperationException();
    }
}