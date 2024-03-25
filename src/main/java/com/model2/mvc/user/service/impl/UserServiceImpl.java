package com.model2.mvc.user.service.impl;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.SearchCondition;
import com.model2.mvc.common.util.RandomSerialGenerator;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.mail.MailAgent;
import com.model2.mvc.mail.MailTransferException;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.dto.request.ListUserRequestDTO;
import com.model2.mvc.user.dto.response.CheckDuplicateResponseDto;
import com.model2.mvc.user.dto.response.ListUserResponseDto;
import com.model2.mvc.user.repository.UserRepository;
import com.model2.mvc.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Primary
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MailAgent mailAgent;

    @Value("#{constantProperties['defaultPageSize']}")
    private int defaultPageSize;

    public void addUser(User userVO) throws Exception {
        userVO.setRegDate(LocalDate.now());
        userRepository.insertUser(userVO);
    }

    public User loginUser(User user) throws Exception {
        Optional<User> dbUser = userRepository.findByUserId(user.getUserId());

        if (dbUser.isPresent()) {
            User du = dbUser.get();
            if (!du.getPassword().equals(user.getPassword())) {
                throw new Exception("No such user");
            }
        }

        return dbUser.orElseThrow(() -> new Exception("No such user"));
    }

    public User getUser(String userId) throws Exception {

        return userRepository.findByUserId(userId).orElseThrow(Exception::new);
    }

    public ListUserResponseDto getUserList(ListUserRequestDTO requestDTO) throws Exception {
        Integer page = requestDTO.getPage();
        page = page == null ? 1 : page;
        Integer pageSize = requestDTO.getPageSize();
        pageSize = pageSize == null ? defaultPageSize : pageSize;
        SearchCondition searchCondition = requestDTO.getSearchCondition() == null
                                          ? SearchCondition.BY_NAME
                                          : requestDTO.getSearchCondition();
        Search search = new Search();
        search.setStartRowNum((page - 1) * pageSize + 1);
        search.setEndRowNum(page * pageSize);
        search.setSearchCondition(searchCondition.getConditionCode());
        search.setSearchKeyword(StringUtil.null2nullStr(requestDTO.getSearchKeyword()));
        switch (searchCondition) {
        case BY_NAME:
            ListData<User> userList = userRepository.findByUserName(search);
            Map<String, Object> result = new HashMap<>();
            result.put("count", userList.getCount());
            result.put("list", userList.getList());
            result.put("searchVO", search);
            int totalPage = 0;
            int total = (int)result.get("count");
            if (total > 0) {
                totalPage = total / pageSize;
                if (total % pageSize > 0) {
                    totalPage += 1;
                }
            }
            return ListUserResponseDto.builder()
                    .total(userList.getCount())
                    .list(userList.getList())
                    .searchVO(search)
                    .currentPage(page)
                    .totalPage(totalPage)
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

    @Override
    public String sendAuthenticateMail(String receiverMailAddress) throws MailTransferException {
        JSONObject metadata = readMailMetadata();
        String generatedCode = RandomSerialGenerator.generate(40);
        this.mailAgent.send(receiverMailAddress,
                            new java.util.Date(),
                            (String)metadata.get("subject"),
                            (String)metadata.get("message"),
                            (String)metadata.get("authenticationURL") +
                            generatedCode +
                            "&authenticatedEmail=" +
                            receiverMailAddress);
        return generatedCode;
    }

    private JSONObject readMailMetadata() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("constants/authenticate-mail.json");
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return (JSONObject)JSONValue.parse(sb.toString());
    }
}