package com.model2.mvc.user.controller;

import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.dto.request.ListUserRequestDTO;
import com.model2.mvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute("user") User user) throws Exception {
        this.userService.addUser(user);
        return "redirect:/users/account/sign-in";
    }

    @GetMapping("/account/signup-form")
    public String addUserView() {
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
    public String login(@ModelAttribute("user") User user, HttpSession session) throws Exception {
        User dbVO = this.userService.loginUser(user);
        session.setAttribute("user", dbVO);
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
