package com.model2.mvc.user.controller;

import com.model2.mvc.mail.MailTransferException;
import com.model2.mvc.user.controller.editor.RoleEditor;
import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.dto.response.CheckDuplicateResponseDto;
import com.model2.mvc.user.dto.response.SignInResponseDto;
import com.model2.mvc.user.dto.response.UserDto;
import com.model2.mvc.user.service.MailAuthorizationService;
import com.model2.mvc.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/users/account")
@RequiredArgsConstructor
public class AccountApi {
    private final UserService userService;
    private final MailAuthorizationService mailAuthorizationService;

    @InitBinder
    public void bindParameters(WebDataBinder binder) {
        binder.registerCustomEditor(Role.class, RoleEditor.getInstance());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User toCreate) throws Exception {
        if (!this.mailAuthorizationService.checkAuthorization(toCreate.getEmail())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        this.userService.addUser(toCreate);
        this.mailAuthorizationService.removeAuthenticationInfo(toCreate.getEmail());
        return new ResponseEntity<>(toCreate, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<UserDto> getLoginUser(@SessionAttribute(value = "user", required = false) User loginUser) {
        if (loginUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(UserDto.from(loginUser), HttpStatus.OK);
    }

    @PostMapping("/duplicate")
    public ResponseEntity<CheckDuplicateResponseDto> checkDuplication(@RequestParam("userId") String userId)
    throws Exception {
        return new ResponseEntity<>(this.userService.checkDuplication(userId), HttpStatus.OK);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody User user, HttpSession session) throws Exception {
        try {
            User dbVO = this.userService.loginUser(user);
            session.setAttribute("user", dbVO);
            return new ResponseEntity<>(new SignInResponseDto(true), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/sign-out")
    public ResponseEntity<String> signOut(HttpSession session, @SessionAttribute("user") User loginUser) {
        session.removeAttribute("user");
        return new ResponseEntity<>(loginUser.getUserId(), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}") // TODO
    public ResponseEntity<User> deleteUser(@PathVariable("userId") String userId, HttpSession session) {
        try {
            User result = this.userService.deleteUser(userId);
            session.removeAttribute("user");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/authentication/start")
    public ResponseEntity<Void> requestAuthenticationMail(@RequestParam("emailAddress") String emailAddress)
    throws MailTransferException {
        this.mailAuthorizationService.sendAuthenticationMail(emailAddress);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/authentication")
    public ResponseEntity<Void> validateAuthentication(@RequestParam("code") String code,
                                                       @RequestParam("authenticatedEmail") String authenticatedEmail) {
        boolean authorized = this.mailAuthorizationService.checkValidCode(authenticatedEmail, code);
        if (!authorized) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
