package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.domain.User;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class LoginAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User userVO = new User();
        userVO.setUserId(request.getParameter("userId"));
        userVO.setPassword(request.getParameter("password"));

        UserService service = new UserServiceImpl();
        User dbVO = service.loginUser(userVO);

        HttpSession session = request.getSession();
        session.setAttribute("user", dbVO);

        return "redirect:/index.jsp";
    }
}