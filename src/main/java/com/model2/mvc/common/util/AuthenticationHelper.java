package com.model2.mvc.common.util;

import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

public class AuthenticationHelper {

    public static boolean authenticate(HttpServletRequest request, HttpServletResponse response, Role toBe, HttpMethod... method) {
        if (Arrays.stream(method).noneMatch(m -> m.matches(request.getMethod()))) {
            return true;
        }
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("user");
        if (loginUser == null || loginUser.getRole() != toBe) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }
        return true;
    }
}
