package com.model2.mvc.user.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.model2.mvc.common.binder.jackson.LocalDateDeserializer;
import com.model2.mvc.common.binder.jackson.LocalDateSerializer;
import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@Getter
@ToString
public class UserDto {
    private String userId;
    private String userName;
    private String password;
    private Role role;
    private String ssn;
    private String phone;
    private String addr;
    private String email;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate regDate;

    public static UserDto from(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .password(user.getPassword())
                .role(user.getRole())
                .ssn(user.getSsn())
                .phone(user.getPhone())
                .addr(user.getAddr())
                .email(user.getEmail())
                .regDate(user.getRegDate())
                .build();
    }
}
