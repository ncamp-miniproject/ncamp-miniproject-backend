package com.model2.mvc.user.controller;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.dto.BasicJSONResponse;
import com.model2.mvc.common.util.mail.MailTransferException;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.dto.request.ListUserRequestDTO;
import com.model2.mvc.user.dto.response.ListUserResponseDTO;
import com.model2.mvc.user.dto.response.SignInResponseDTO;
import com.model2.mvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/users")
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/account/new")
    public ResponseEntity<BasicJSONResponse> createUser(@RequestBody User toCreate, HttpSession session)
    throws Exception {
        Boolean authenticated = (Boolean)session.getAttribute("authenticated");
        if (authenticated == null || !authenticated) {
            return BasicJSONResponse.forbidden();
        }
        this.userService.addUser(toCreate);
        return BasicJSONResponse.created(toCreate);
    }

    @PostMapping("/account/check-duplicate")
    public ResponseEntity<BasicJSONResponse> checkDuplication(@RequestParam("userId") String userId) throws Exception {
        boolean result = this.userService.checkDuplication(userId);
        return BasicJSONResponse.ok(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BasicJSONResponse> getUser(@PathVariable("userId") String userId) throws Exception {
        User user = this.userService.getUser(userId);
        return BasicJSONResponse.ok(user);
    }

    @GetMapping("")
    public ResponseEntity<BasicJSONResponse> getUsers(@RequestBody ListUserRequestDTO requestDTO) throws Exception {
        Map<String, Object> result = this.userService.getUserList(requestDTO);

        int totalPage = 0;
        int total = (int)result.get("count");
        if (total > 0) {
            totalPage = total / requestDTO.getPageSize();
            if (total % requestDTO.getPageSize() > 0) {
                totalPage += 1;
            }
        }

        ListUserResponseDTO responseData = ListUserResponseDTO.builder()
                .total((int)result.get("count"))
                .list((List<User>)result.get("list"))
                .searchVO((Search)result.get("searchVO"))
                .currentPage(requestDTO.getPage() == 0 ? 1 : requestDTO.getPage())
                .totalPage(totalPage)
                .build();

        return BasicJSONResponse.ok(responseData);
    }

    @PostMapping("/account/sign-in")
    public ResponseEntity<BasicJSONResponse> signIn(@RequestBody User user, HttpSession session) throws Exception {
        try {
            User dbVO = this.userService.loginUser(user);
            session.setAttribute("user", dbVO);
            return BasicJSONResponse.ok(new SignInResponseDTO(true));
        } catch (Exception e) {
            return BasicJSONResponse.unauthorized();
        }
    }

    @PostMapping("/account/sign-out")
    public ResponseEntity<BasicJSONResponse> signOut(HttpSession session, @SessionAttribute("user") User loginUser) {
        session.removeAttribute("user");
        return BasicJSONResponse.ok(loginUser.getUserId());
    }

    @PostMapping("/account/{userId}/delete")
    public ResponseEntity<BasicJSONResponse> deleteUser(@PathVariable("userId") String userId, HttpSession session) {
        try {
            User result = this.userService.deleteUser(userId);
            session.removeAttribute("user");
            return BasicJSONResponse.ok(result);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @PostMapping("/account/authentication/start")
    public ResponseEntity<BasicJSONResponse> requestAuthenticationMail(@RequestParam("emailAddress") String emailAddress,
                                                                       HttpSession session)
    throws MailTransferException {
        String generatedCode = this.userService.sendAuthenticateMail(emailAddress);
        session.setAttribute("authenticationCode", generatedCode);
        return BasicJSONResponse.noContent();
    }

    @PostMapping("/account/authentication")
    public ResponseEntity<BasicJSONResponse> validateAuthentication(@RequestParam("code") String code,
                                                                    HttpSession session) {
        Object authenticationCode = session.getAttribute("authenticationCode");
        if (authenticationCode == null) {
            return BasicJSONResponse.forbidden();
        }
        if (!code.equals(authenticationCode)) {
            return BasicJSONResponse.unauthorized();
        }
        session.removeAttribute("authenticationCode");
        session.setAttribute("authenticated", true);
        return BasicJSONResponse.ok(true);
    }
}
