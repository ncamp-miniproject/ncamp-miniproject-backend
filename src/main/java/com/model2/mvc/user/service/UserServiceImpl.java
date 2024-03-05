package com.model2.mvc.user.service;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.Search;
import com.model2.mvc.user.dao.UserDAO;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.dto.request.ListUserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Primary
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
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

    public Map<String, Object> getUserList(ListUserRequestDTO requestDTO) throws Exception {
        Search search = new Search();
        int page = requestDTO.getPage();
        int pageSize = requestDTO.getPageSize();
        search.setStartRowNum((page - 1) * pageSize + 1);
        search.setEndRowNum(page * pageSize);
        search.setSearchCondition(requestDTO.getSearchCondition());
        search.setSearchKeyword(requestDTO.getSearchKeyword());
        switch (requestDTO.getSearchCondition()) {
        case "0":
            ListData<User> userList = userDAO.findByUserName(search);
            Map<String, Object> result = new HashMap<>();
            result.put("count", userList.getCount());
            result.put("list", userList.getList());
            result.put("searchVO", search);
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