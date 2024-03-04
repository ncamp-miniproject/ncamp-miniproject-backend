package com.model2.mvc.user.controller;

import com.model2.mvc.framework.Action;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("updateUserViewAction")
public class UpdateUserViewAction extends Action {
    private UserService userService;

    @Autowired
    public UpdateUserViewAction(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");

        User user = this.userService.getUser(userId);

        System.out.println(user);

        request.setAttribute("user", user);

        return "forward:/user/updateUser.jsp";
    }
}
