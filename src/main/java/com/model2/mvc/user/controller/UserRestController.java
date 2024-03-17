package com.model2.mvc.user.controller;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.mail.MailTransferException;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.dto.request.ListUserRequestDTO;
import com.model2.mvc.user.dto.response.CheckDuplicateResponseDTO;
import com.model2.mvc.user.dto.response.ListUserResponseDTO;
import com.model2.mvc.user.dto.response.SignInResponseDTO;
import com.model2.mvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public ResponseEntity<User> createUser(@RequestBody User toCreate, HttpSession session) throws Exception {
        Boolean authenticated = (Boolean)session.getAttribute("authenticated");
        if (authenticated == null || !authenticated) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        this.userService.addUser(toCreate);
        return new ResponseEntity<>(toCreate, HttpStatus.CREATED);
    }

    @PostMapping("/account/check-duplicate")
    public ResponseEntity<CheckDuplicateResponseDTO> checkDuplication(@RequestParam("userId") String userId)
    throws Exception {
        boolean result = this.userService.checkDuplication(userId);
        return new ResponseEntity<>(new CheckDuplicateResponseDTO(result, userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId) throws Exception {
        User user = this.userService.getUser(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<ListUserResponseDTO> getUsers(@ModelAttribute ListUserRequestDTO requestDTO)
    throws Exception {
        Map<String, Object> result = this.userService.getUserList(requestDTO);

        int page = requestDTO.getPage() == null ? 1 : requestDTO.getPage();
        int pageSize = requestDTO.getPageSize() == null ? 1 : requestDTO.getPageSize();

        int totalPage = 0;
        int total = (int)result.get("count");
        if (total > 0) {
            totalPage = total / pageSize;
            if (total % pageSize > 0) {
                totalPage += 1;
            }
        }

        ListUserResponseDTO responseData = ListUserResponseDTO.builder()
                .total((int)result.get("count"))
                .list((List<User>)result.get("list"))
                .searchVO((Search)result.get("searchVO"))
                .currentPage(page)
                .totalPage(totalPage)
                .build();

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/account/sign-in")
    public ResponseEntity<SignInResponseDTO> signIn(@RequestBody User user, HttpSession session) throws Exception {
        try {
            User dbVO = this.userService.loginUser(user);
            session.setAttribute("user", dbVO);
            return new ResponseEntity<>(new SignInResponseDTO(true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/account/sign-out")
    public ResponseEntity<String> signOut(HttpSession session, @SessionAttribute("user") User loginUser) {
        session.removeAttribute("user");
        return new ResponseEntity<>(loginUser.getUserId(), HttpStatus.OK);
    }

    @PostMapping("/account/{userId}/delete")
    public ResponseEntity<User> deleteUser(@PathVariable("userId") String userId, HttpSession session) {
        try {
            User result = this.userService.deleteUser(userId);
            session.removeAttribute("user");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/account/authentication/start")
    public ResponseEntity<Void> requestAuthenticationMail(@RequestParam("emailAddress") String emailAddress,
                                                          HttpSession session) throws MailTransferException {
        String generatedCode = this.userService.sendAuthenticateMail(emailAddress);
        session.setAttribute("authenticationCode", generatedCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/account/authentication")
    public ResponseEntity<Void> validateAuthentication(@RequestParam("code") String code, HttpSession session) {
        Object authenticationCode = session.getAttribute("authenticationCode");
        if (authenticationCode == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (!code.equals(authenticationCode)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        session.removeAttribute("authenticationCode");
        session.setAttribute("authenticated", true);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
