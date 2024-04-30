package com.model2.mvc.user.controller;

import com.model2.mvc.user.controller.editor.RoleEditor;
import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.dto.request.ListUserRequestDto;
import com.model2.mvc.user.dto.response.CheckDuplicateResponseDto;
import com.model2.mvc.user.dto.response.ListUserResponseDto;
import com.model2.mvc.user.dto.response.SignInResponseDto;
import com.model2.mvc.user.dto.response.UserResponseDto;
import com.model2.mvc.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @InitBinder
    public void bindParameters(WebDataBinder binder) {
        binder.registerCustomEditor(Role.class, RoleEditor.getInstance());
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute("user") User user)
    throws Exception {
        URI uri = new URIBuilder().setScheme("http")
                .setHost("localhost")
                .setPort(8089)
                .setPath("/api/users/account")
                .build();
        RequestEntity<User> requestEntity = RequestEntity.post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(user);
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.exchange(requestEntity, User.class);
        } catch (HttpClientErrorException.Forbidden e) {
            return "user/notification-email-not-good";
        }
        return "redirect:/";
    }

    @GetMapping("/account/email-auth")
    public String emailAuthForm() {
        return "user/email-auth";
    }

    @PostMapping("/account/email-auth")
    public String emailAuth(@RequestParam("emailAddress") String emailAddress) throws URISyntaxException {
        URI uri = new URIBuilder().setScheme("http")
                .setHost("localhost")
                .setPort(8089)
                .setPath("/api/users/account/authentication/start")
                .setParameter("emailAddress", emailAddress)
                .build();
        RequestEntity<Void> requestEntity = RequestEntity.post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .build();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(requestEntity, Void.class);
        return "user/email-send-notification";
    }

    @GetMapping("/account/authentication/code")
    public ModelAndView authenticationCodeValidation(@RequestParam("code") String authenticationCode,
                                                     @RequestParam("authenticatedEmail") String authenticatedEmail)
    throws URISyntaxException {
        URI uri = new URIBuilder().setScheme("http")
                .setHost("localhost")
                .setPort(8089)
                .setPath("/api/users/account/authentication")
                .setParameters(new BasicNameValuePair("code", authenticationCode),
                               new BasicNameValuePair("authenticatedEmail", authenticatedEmail))
                .build();
        RequestEntity<Void> requestEntity = RequestEntity.post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .build();
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.exchange(requestEntity, Void.class);
        } catch (HttpClientErrorException.Forbidden | HttpClientErrorException.Unauthorized e) {
            e.printStackTrace();
            return new ModelAndView("user/notification-email-not-good");
        }
        return new ModelAndView("user/notification-email-good", "authenticatedEmail", authenticatedEmail);
    }

    @GetMapping("/account/signup-form")
    public String signUpForm() {
        return "user/sign-up-form";
    }

    @PostMapping("/account/check-duplicate")
    public String checkDuplication(@RequestParam("userId") String userId, Model model) throws Exception {
        URI uri = new URIBuilder().setScheme("http")
                .setHost("localhost")
                .setPort(8089)
                .setPath("/api/users/account/duplicate")
                .setParameter("userId", userId)
                .build();
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<CheckDuplicateResponseDto> response = restTemplate.exchange(RequestEntity.post(uri)
                                                                                               .contentType(MediaType.APPLICATION_JSON)
                                                                                               .build(),
                                                                                       CheckDuplicateResponseDto.class);
            CheckDuplicateResponseDto body = response.getBody();
            if (body != null) {
                model.addAttribute("result", body.getResult());
                model.addAttribute("userId", body.getUserId());
            }
        } catch (HttpClientErrorException e) {
            e.printStackTrace(); // TODO
        }
        return "user/duplicate-check";
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable("userId") String userId, Model model) throws Exception {
        URI uri = new URIBuilder().setScheme("http")
                .setHost("localhost")
                .setPort(8089)
                .setPath("/api/users/" + userId)
                .build();
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<User> response = restTemplate.exchange(RequestEntity.get(uri)
                                                                          .accept(MediaType.APPLICATION_JSON)
                                                                          .build(), User.class);
            model.addAttribute("currentUser", response.getBody());
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException();
        }
        return "user/user-info";
    }

    @GetMapping("")
    public String listUser(@ModelAttribute("requestDTO") ListUserRequestDto requestDTO, Model model) throws Exception {
        URIBuilder uriBuilder = new URIBuilder().setScheme("http")
                .setHost("localhost")
                .setPort(8089)
                .setPath("/api/users");
        if (requestDTO.getPage() != null) {
            uriBuilder.addParameter("page", requestDTO.getPage().toString());
        }
        if (requestDTO.getPageSize() != null) {
            uriBuilder.addParameter("pageSize", requestDTO.getPageSize().toString());
        }
        if (requestDTO.getSearchKeyword() != null) {
            uriBuilder.addParameter("searchKeyword", requestDTO.getSearchKeyword());
        }
        if (requestDTO.getSearchCondition() != null) {
            uriBuilder.addParameter("searchCondition", requestDTO.getSearchCondition().getConditionCode());
        }
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<ListUserResponseDto> response = restTemplate.exchange(RequestEntity.get(uriBuilder.build())
                                                                                         .accept(MediaType.APPLICATION_JSON)
                                                                                         .build(),
                                                                                 ListUserResponseDto.class);
            ListUserResponseDto result = response.getBody();
            if (result != null) {
                model.addAttribute("count", result.getCount());
                model.addAttribute("list", result.getList());
                model.addAttribute("paginationInfo", result.getPaginationInfo());
            }
        } catch (HttpClientErrorException.Unauthorized e) {
            e.printStackTrace();
            return "redirect:/";
        }
        return "user/user-list";
    }

    @PostMapping("/account/sign-in")
    public String signIn(@ModelAttribute("user") User user, @CookieValue("JSESSIONID") String jSessionId)
    throws Exception {
        URI uri = new URIBuilder().setScheme("http")
                .setHost("localhost")
                .setPort(8089)
                .setPath("/api/users/account/sign-in")
                .build();
        RequestEntity<User> requestEntity = RequestEntity.post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .header("Cookie", "JSESSIONID=" + jSessionId)
                .body(user);
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.exchange(requestEntity, SignInResponseDto.class);
        } catch (HttpClientErrorException.Unauthorized e) {
            System.err.println(e.getStatusCode());
            System.err.println(e.getResponseBodyAsString());
        }
        return "redirect:/";
    }

    @GetMapping("/account/sign-in")
    public String signInForm() {
        return "user/sign-in";
    }

    @PostMapping("/account/sign-out")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, HttpSession session) throws Exception {
        this.userService.updateUser(user);

        String sessionId = ((User)session.getAttribute("user")).getUserId();
        if (sessionId.equals(user.getUserId())) {
            session.setAttribute("user", user);
        }
        return "redirect:/users/" + user.getUserId();
    }

    @GetMapping("/{userId}/update-form")
    public String updateUserView(@PathVariable("userId") String userId, Model model) throws Exception {
        UserResponseDto user = this.userService.getUser(userId);
        model.addAttribute("user", user);
        return "user/update-form";
    }
}
