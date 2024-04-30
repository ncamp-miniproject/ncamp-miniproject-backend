package com.model2.mvc.auth.controller;

import com.model2.mvc.auth.dto.request.AuthRequestDto;
import com.model2.mvc.auth.dto.response.LoginUserResponseDto;
import com.model2.mvc.auth.exception.AuthRequestFailException;
import com.model2.mvc.auth.service.AuthService;
import com.model2.mvc.auth.service.RegisterAuthenticationService;
import com.model2.mvc.auth.dto.response.AuthenticatedResponseDto;
import com.model2.mvc.user.controller.editor.RoleEditor;
import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.dto.request.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthApi {
    private final AuthService authService;
    private final RegisterAuthenticationService registerAuthService;

    @InitBinder
    public void bindParameters(WebDataBinder binder) {
        binder.registerCustomEditor(Role.class, RoleEditor.getInstance());
    }

    @PostMapping("/registration")
    public ResponseEntity<AuthenticatedResponseDto> registerUser(@RequestBody RegisterRequestDto request) {
        try {
            AuthenticatedResponseDto result = this.authService.registerUser(request);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<AuthenticatedResponseDto> authenticate(@RequestBody AuthRequestDto request) {
        try {
            AuthenticatedResponseDto result = this.authService.authenticate(request);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/token/{token}")
    public ResponseEntity<LoginUserResponseDto> verifyToken(@PathVariable("token") String token) {
        try {
            LoginUserResponseDto result = this.authService.verifyToken(token);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/sign-out")
    public ResponseEntity<String> signOut(HttpSession session, @SessionAttribute("user") User loginUser) {
        session.removeAttribute("user");
        return new ResponseEntity<>(loginUser.getUserId(), HttpStatus.OK);
    }

    @PostMapping("/registration/auth/start")
    public ResponseEntity<Void> requestAuthenticationMail(@RequestParam("emailAddress") String emailAddress)
    throws AuthRequestFailException {
        this.registerAuthService.sendAuthenticationRequest(emailAddress);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/registration/auth") // TODO: Some proxy task should be involved
    public ResponseEntity<Void> validateAuthentication(@RequestParam("code") String code,
                                                       @RequestParam("authenticatedEmail") String authenticatedEmail) {
        boolean authorized = this.registerAuthService.checkValidCode(authenticatedEmail, code);
        if (!authorized) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
