package com.model2.mvc.user.controller;

import com.model2.mvc.mail.MailTransferException;
import com.model2.mvc.user.controller.editor.RoleEditor;
import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.dto.request.ListUserRequestDTO;
import com.model2.mvc.user.dto.response.CheckDuplicateResponseDto;
import com.model2.mvc.user.dto.response.ListUserResponseDto;
import com.model2.mvc.user.dto.response.SignInResponseDto;
import com.model2.mvc.user.dto.response.UserDto;
import com.model2.mvc.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @InitBinder
    public void bindParameters(WebDataBinder binder) {
        binder.registerCustomEditor(Role.class, RoleEditor.getInstance());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId) throws Exception {
        User user = this.userService.getUser(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ListUserResponseDto> listUser(@ModelAttribute ListUserRequestDTO requestDTO, @SessionAttribute(value = "user", required = false)
                                                        Optional<User> loginUser)
    throws Exception {
        if (!loginUser.isPresent() || loginUser.get().getRole() != Role.ADMIN) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(this.userService.getUserList(requestDTO), HttpStatus.OK);
    }
}
