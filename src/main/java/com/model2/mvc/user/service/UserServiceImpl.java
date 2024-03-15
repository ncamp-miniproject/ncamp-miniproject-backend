package com.model2.mvc.user.service;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.RandomSerialGenerator;
import com.model2.mvc.common.util.mail.MailAgent;
import com.model2.mvc.common.util.mail.MailTransferException;
import com.model2.mvc.user.dao.UserDAO;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.dto.request.ListUserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Primary
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final MailAgent mailAgent;

    @Value("#{constantProperties['defaultPageSize']}")
    private int defaultPageSize;

    public void addUser(User userVO) throws Exception {
        userVO.setRegDate(new Date(System.currentTimeMillis()));
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
        page = page == 0 ? 1 : page;
        int pageSize = requestDTO.getPageSize();
        pageSize = pageSize == 0 ? defaultPageSize : pageSize;
        search.setStartRowNum((page - 1) * pageSize + 1);
        search.setEndRowNum(page * pageSize);
        search.setSearchCondition(requestDTO.getSearchCondition().getConditionCode());
        search.setSearchKeyword(requestDTO.getSearchKeyword());
        switch (requestDTO.getSearchCondition()) {
        case BY_ID:
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

    @Override
    public User deleteUser(String userId) throws Exception {
        User found = this.userDAO.findByUserId(userId);
        if (found == null) {
            throw new Exception(); // TODO
        }
        this.userDAO.removeByUserId(userId);
        return found;
    }

    @Override
    public String sendAuthenticateMail(String receiverMailAddress) throws MailTransferException {
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
        JSONObject metadata = (JSONObject)JSONValue.parse(sb.toString());
        String generatedCode = RandomSerialGenerator.generate(40);
        this.mailAgent.send(receiverMailAddress,
                            new java.util.Date(),
                            (String)metadata.get("subject"),
                            (String)metadata.get("message"),
                            (String)metadata.get("authenticationURL") + generatedCode);
        return generatedCode;
    }
}