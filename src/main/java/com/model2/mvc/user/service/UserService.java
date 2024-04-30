package com.model2.mvc.user.service;

import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.dto.request.ListUserRequestDto;
import com.model2.mvc.user.dto.request.SignInRequestDto;
import com.model2.mvc.user.dto.response.CheckDuplicateResponseDto;
import com.model2.mvc.user.dto.response.ListUserResponseDto;
import com.model2.mvc.user.dto.response.UserResponseDto;

public interface UserService {

    public User signIn(SignInRequestDto userVO) throws Exception;

    public UserResponseDto getUser(String userId);

    public UserResponseDto getUserFromToken(String token);

    public ListUserResponseDto getUserList(ListUserRequestDto requestDTO) throws Exception;

    public void updateUser(User userVO) throws Exception;

    public CheckDuplicateResponseDto checkDuplication(String userId) throws Exception;

    public User deleteUser(String userId) throws Exception;
}