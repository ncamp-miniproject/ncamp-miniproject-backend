package com.model2.mvc.user.service;

import com.model2.mvc.common.util.mail.MailTransferException;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.dto.request.ListUserRequestDTO;

import java.util.Map;

public interface UserService {

    public void addUser(User userVO) throws Exception;

    public User loginUser(User userVO) throws Exception;

    public User getUser(String userId) throws Exception;

    public Map<String, Object> getUserList(ListUserRequestDTO requestDTO) throws Exception;

    public void updateUser(User userVO) throws Exception;

    public boolean checkDuplication(String userId) throws Exception;

    public User deleteUser(String userId) throws Exception;

    public String sendAuthenticateMail(String receiverMailAddress) throws MailTransferException;
}