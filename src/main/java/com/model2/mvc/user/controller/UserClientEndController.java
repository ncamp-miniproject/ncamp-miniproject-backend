package com.model2.mvc.user.controller;

import com.model2.mvc.common.dto.BasicJSONResponse;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.dto.request.ListUserRequestDTO;
import com.model2.mvc.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
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
import java.util.Map;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserClientEndController {

    private final UserService userService;

    @PostMapping("/new")
    public String addUser(@ModelAttribute("user") User user, @CookieValue("JSESSIONID") String jSessionId)
    throws Exception {
        URI uri = new URIBuilder().setScheme("http")
                .setHost("localhost")
                .setPort(8089)
                .setPath("/app/users/account/new")
                .build();
        RequestEntity<User> requestEntity = RequestEntity.post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .header("Cookie", "JSESSIONID=" + jSessionId)
                .body(user);
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.exchange(requestEntity, BasicJSONResponse.class);
        } catch (HttpClientErrorException.Forbidden e) {
            return "redirect:/users/account/email-auth";
        }
        return "redirect:/";
    }

    @GetMapping("/account/email-auth")
    public String emailAuthForm() {
        return "user/email-auth";
    }

    @PostMapping("/account/email-auth")
    public String emailAuth(@RequestParam("emailAddress") String emailAddress,
                            @CookieValue("JSESSIONID") String jSessionId) throws URISyntaxException {
        URI uri = new URIBuilder().setScheme("http")
                .setHost("localhost")
                .setPort(8089)
                .setPath("/app/users/account/authentication/start")
                .setParameter("emailAddress", emailAddress)
                .build();
        RequestEntity<Void> requestEntity = RequestEntity.post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .header("Cookie", "JSESSIONID=" + jSessionId)
                .build();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(requestEntity, BasicJSONResponse.class);
        return "user/email-send-notification";
    }

    @GetMapping("/account/authentication/code")
    public ModelAndView authenticationCodeValidation(@RequestParam("code") String authenticationCode,
                                                     @CookieValue("JSESSIONID") String jSessionId)
    throws URISyntaxException {
        URI uri = new URIBuilder().setScheme("http")
                .setHost("localhost")
                .setPort(8089)
                .setPath("/app/users/account/authentication")
                .setParameter("code", authenticationCode)
                .build();
        RequestEntity<Void> requestEntity = RequestEntity.post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .header("Cookie", "JSESSIONID=" + jSessionId)
                .build();
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.exchange(requestEntity, BasicJSONResponse.class);
        } catch (HttpClientErrorException.Forbidden | HttpClientErrorException.Unauthorized e) {
            e.printStackTrace();
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("redirect:/users/account/signup-form");
    }

    @GetMapping("/account/signup-form")
    public String signUpForm() {
        return "user/addUserView";
    }

    @PostMapping("/account/check-duplicate")
    public String checkDuplication(@RequestParam("userId") String userId, Model model) throws Exception {
        boolean result = this.userService.checkDuplication(userId);
        model.addAttribute("result", result);
        model.addAttribute("userId", userId);
        return "user/checkDuplication";
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable("userId") String userId, Model model) throws Exception {
        User user = this.userService.getUser(userId);
        model.addAttribute("user", user);
        return "user/readUser";
    }

    @GetMapping("")
    public String listUser(@ModelAttribute("requestDTO") ListUserRequestDTO requestDTO, Model model) throws Exception {
        Map<String, Object> map = this.userService.getUserList(requestDTO);
        model.addAttribute("total", map.get("count"));
        model.addAttribute("list", map.get("list"));
        model.addAttribute("searchVO", map.get("searchVO"));
        model.addAttribute("currentPage", requestDTO.getPage() == 0 ? 1 : requestDTO.getPage());

        int totalPage = 0;
        int total = (int)map.get("count");
        if (total > 0) {
            totalPage = total / requestDTO.getPageSize();
            if (total % requestDTO.getPageSize() > 0) {
                totalPage += 1;
            }
        }
        model.addAttribute("totalPage", totalPage);
        return "user/listUser";
    }

    @PostMapping("/account/sign-in")
    public String login(@ModelAttribute("user") User user, @CookieValue("JSESSIONID") String jSessionId)
    throws Exception {
        URI uri = new URIBuilder().setScheme("http")
                .setHost("localhost")
                .setPort(8089)
                .setPath("/app/users/account/sign-in")
                .build();
        RequestEntity<User> requestEntity = RequestEntity.post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .header("Cookie", "JSESSIONID=" + jSessionId)
                .body(user);
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.exchange(requestEntity, BasicJSONResponse.class);
        } catch (HttpClientErrorException.Unauthorized e) {
            System.err.println(e.getStatusCode());
            System.err.println(e.getResponseBodyAsString());
        }
        return "redirect:/";
    }

    @GetMapping("/account/sign-in")
    public String loginForm() {
        return "user/loginView";
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
        User user = this.userService.getUser(userId);
        model.addAttribute("user", user);
        return "user/updateUser";
    }
}
