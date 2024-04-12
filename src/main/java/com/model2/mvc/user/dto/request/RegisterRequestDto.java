package com.model2.mvc.user.dto.request;

import com.model2.mvc.user.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class RegisterRequestDto {
    private String userId;
    private String nameOfUser;
    private String password;
    private Role role;
    private String ssn;
    private String phone;
    private String addr;
    private String email;
}
