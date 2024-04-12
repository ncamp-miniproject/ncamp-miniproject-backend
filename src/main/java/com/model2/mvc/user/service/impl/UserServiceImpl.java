package com.model2.mvc.user.service.impl;

import com.model2.mvc.common.Pagination;
import com.model2.mvc.common.SearchCondition;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.dto.request.ListUserRequestDto;
import com.model2.mvc.user.dto.request.SignInRequestDto;
import com.model2.mvc.user.dto.response.CheckDuplicateResponseDto;
import com.model2.mvc.user.dto.response.ListUserResponseDto;
import com.model2.mvc.user.repository.UserRepository;
import com.model2.mvc.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Primary
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Value("#{constantProperties['defaultPageSize']}")
    private int defaultPageSize;

    @Value("#{constantProperties['defaultPageDisplay']}")
    private int defaultPageDisplay;

    @Override
    public User signIn(SignInRequestDto dto) throws Exception {
        Optional<User> dbUser = userRepository.findByUserId(dto.getUserId());

        if (dbUser.isPresent()) {
            User du = dbUser.get();
            if (!du.getPassword().equals(dto.getPassword())) {
                throw new Exception("No such user");
            }
        }

        return dbUser.orElseThrow(() -> new Exception("No such user"));
    }

    public User getUser(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(IllegalArgumentException::new);
    }

    public ListUserResponseDto getUserList(ListUserRequestDto requestDto) throws Exception {
        Integer page = requestDto.getPage();
        page = page == null ? 1 : page;
        Integer pageSize = requestDto.getPageSize();
        pageSize = pageSize == null ? defaultPageSize : pageSize;
        SearchCondition searchCondition = requestDto.getSearchCondition() == null
                                          ? SearchCondition.NO_CONDITION
                                          : requestDto.getSearchCondition();
        String searchKeyword = StringUtil.null2nullStr(requestDto.getSearchKeyword());
        switch (searchCondition) {
        case BY_NAME:
            List<User> listResult = this.userRepository.findByUserName(searchKeyword, false, page, pageSize);
            int countResult = this.userRepository.countByUserName(searchKeyword, false);
            return ListUserResponseDto.builder()
                    .count(countResult)
                    .list(listResult)
                    .paginationInfo(Pagination.of(page, countResult, pageSize, defaultPageDisplay))
                    .build();
        default:
            throw new IllegalArgumentException();
        }
    }

    public void updateUser(User user) throws Exception {
        userRepository.updateUser(user);
    }

    public CheckDuplicateResponseDto checkDuplication(String userId) throws Exception {
        boolean result = false;
        Optional<User> userVO = userRepository.findByUserId(userId);
        if (userVO.isPresent()) {
            result = true;
        }
        return new CheckDuplicateResponseDto(result, userId);
    }

    @Override
    public User deleteUser(String userId) throws Exception {
        Optional<User> found = this.userRepository.findByUserId(userId);
        found.orElseThrow(Exception::new);
        this.userRepository.removeByUserId(userId);
        return found.get();
    }
}