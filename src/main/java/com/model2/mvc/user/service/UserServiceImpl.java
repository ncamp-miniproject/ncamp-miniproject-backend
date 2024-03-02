package com.model2.mvc.user.service;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.dto.Search;
import com.model2.mvc.user.dao.UserDAO;
import com.model2.mvc.user.dao.impl.PlainJDBCUserDAO;
import com.model2.mvc.user.domain.User;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addUser(User userVO) throws Exception {
        userDAO.insertUser(userVO);
    }

    public User loginUser(User user) throws Exception {
        User dbUser = userDAO.findByUserId(user.getUserId());

        if (!dbUser.getPassword().equals(user.getPassword())) {
            throw new Exception("No such user");
        }

        return dbUser;
    }

    public User getUser(String userId) throws Exception {
        return userDAO.findByUserId(userId);
    }

    public Map<String, Object> getUserList(Search searchVO) throws Exception {
        switch (searchVO.getSearchCondition()) {
        case "0":
            ListData<User> userList = userDAO.findByUserName(searchVO.getSearchKeyword());
            Map<String, Object> result = new HashMap<>();
            result.put("count", userList.getCount());
            result.put("list", userList.getList());
            return result;
        default:
            throw new IllegalArgumentException();
        }
    }

    public void updateUser(User user) throws Exception {
        userDAO.updateUser(user);
    }

    public boolean checkDuplication(String userId) throws Exception {
        boolean result = true;
        User userVO = userDAO.findByUserId(userId);
        if (userVO != null) {
            result = false;
        }
        return result;
    }
}