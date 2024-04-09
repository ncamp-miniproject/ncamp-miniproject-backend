package com.model2.mvc.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MailAuthenticationInfo {
    private String email;
    private String authenticationCode;
    private boolean authenticated;
}
