package com.model2.mvc.user.service;

import com.model2.mvc.mail.MailTransferException;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.dto.request.ListUserRequestDTO;
import com.model2.mvc.user.dto.response.CheckDuplicateResponseDto;
import com.model2.mvc.user.dto.response.ListUserResponseDto;

public interface UserService {

    public void addUser(User userVO) throws Exception;

    public User loginUser(User userVO) throws Exception;

    public User getUser(String userId) throws Exception;

    public ListUserResponseDto getUserList(ListUserRequestDTO requestDTO) throws Exception;

    public void updateUser(User userVO) throws Exception;

    public CheckDuplicateResponseDto checkDuplication(String userId) throws Exception;

    public User deleteUser(String userId) throws Exception;
}