package com.model2.mvc.user.controller;

import com.model2.mvc.common.SearchCondition;
import com.model2.mvc.common.propertyeditor.SearchConditionEditor;
import com.model2.mvc.user.controller.editor.RoleEditor;
import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.dto.request.ListUserRequestDto;
import com.model2.mvc.user.dto.response.CheckDuplicateResponseDto;
import com.model2.mvc.user.dto.response.ListUserResponseDto;
import com.model2.mvc.user.dto.response.UserResponseDto;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @InitBinder
    public void bindParameters(WebDataBinder binder) {
        binder.registerCustomEditor(Role.class, RoleEditor.getInstance());
        binder.registerCustomEditor(SearchCondition.class, SearchConditionEditor.getInstance());
    }

    @PostMapping("/duplicate")
    public ResponseEntity<CheckDuplicateResponseDto> checkDuplication(@RequestParam("userId") String userId)
    throws Exception {
        return new ResponseEntity<>(this.userService.checkDuplication(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}") // TODO
    public ResponseEntity<User> deleteUser(@PathVariable("userId") String userId) {
        try {
            User result = this.userService.deleteUser(userId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("userId") String userId) {
        try {
            User user = this.userService.getUser(userId);
            return new ResponseEntity<>(UserResponseDto.from(user), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<ListUserResponseDto> listUser(@ModelAttribute ListUserRequestDto requestDTO)
    throws Exception {
        return new ResponseEntity<>(this.userService.getUserList(requestDTO), HttpStatus.OK);
    }
}
