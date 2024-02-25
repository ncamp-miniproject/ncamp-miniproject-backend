package com.model2.mvc.user.service;

import com.model2.mvc.common.dto.Search;
import com.model2.mvc.user.dao.UserDAO;
import com.model2.mvc.user.domain.User;

import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl() {
        userDAO = new UserDAO();
    }

    public void addUser(User userVO) throws Exception {
        userDAO.insertUser(userVO);
    }

    public User loginUser(User userVO) throws Exception {
        User dbUser = userDAO.findUser(userVO.getUserId());

        if (!dbUser.getPassword().equals(userVO.getPassword())) {
            throw new Exception("로그인에 실패했습니다.");
        }

        return dbUser;
    }

    public User getUser(String userId) throws Exception {
        return userDAO.findUser(userId);
    }

    public Map<String, Object> getUserList(Search searchVO) throws Exception {
        return userDAO.getUserList(searchVO);
    }

    public void updateUser(User userVO) throws Exception {
        userDAO.updateUser(userVO);
    }

    public boolean checkDuplication(String userId) throws Exception {
        boolean result = true;
        User userVO = userDAO.findUser(userId);
        if (userVO != null) {
            result = false;
        }
        return result;
    }
}