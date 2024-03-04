package com.model2.mvc.user.controller;

import com.model2.mvc.framework.Action;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component("updateUserAction")
public class UpdateUserAction extends Action {
    private UserService userService;

    @Autowired
    public UpdateUserAction(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = (String)request.getParameter("userId");

        User userVO = new User();
        userVO.setUserId(userId);
        userVO.setUserName(request.getParameter("userName"));
        userVO.setAddr(request.getParameter("addr"));
        userVO.setPhone(request.getParameter("phone"));
        userVO.setEmail(request.getParameter("email"));

        this.userService.updateUser(userVO);

        HttpSession session = request.getSession();
        String sessionId = ((User)session.getAttribute("user")).getUserId();

        if (sessionId.equals(userId)) {
            session.setAttribute("user", userVO);
        }

        return "redirect:/getUser.do?userId=" + userId;
    }
}