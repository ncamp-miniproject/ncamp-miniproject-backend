package com.model2.mvc.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.service.UserService;
import com.model2.mvc.user.service.UserServiceImpl;

public class UpdateUserViewAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");

        UserService service = new UserServiceImpl();
        User user = service.getUser(userId);
        
        System.out.println(user);

        request.setAttribute("user", user);

        return "forward:/user/updateUser.jsp";
    }
}
