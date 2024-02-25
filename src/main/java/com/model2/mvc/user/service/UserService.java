package com.model2.mvc.user.service;

import com.model2.mvc.common.dto.Search;
import com.model2.mvc.user.domain.User;

import java.util.Map;

public interface UserService {

    public void addUser(User userVO) throws Exception;

    public User loginUser(User userVO) throws Exception;

    public User getUser(String userId) throws Exception;

    public Map<String, Object> getUserList(Search searchVO) throws Exception;

    public void updateUser(User userVO) throws Exception;

    public boolean checkDuplication(String userId) throws Exception;

}