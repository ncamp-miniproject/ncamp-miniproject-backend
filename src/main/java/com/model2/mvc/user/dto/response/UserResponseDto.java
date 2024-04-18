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
public class UserResponseDto {
    private String userId;
    private String nameOfUser;
    private Role role;
    private String ssn;
    private String phone;
    private String addr;
    private String email;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate regDate;

    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .nameOfUser(user.getNameOfUser())
                .role(user.getRole())
                .ssn(user.getSsn())
                .phone(user.getPhone())
                .addr(user.getAddr())
                .email(user.getEmail())
                .regDate(user.getRegDate())
                .build();
    }
}
