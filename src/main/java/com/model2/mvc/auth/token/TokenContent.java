package com.model2.mvc.auth.token;

import com.model2.mvc.user.domain.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@ToString
public class TokenContent {
    private final String userId;
    private final Role role;
    private final Date expiration;

    public boolean isValid() {
        return this.expiration.after(new Date());
    }
}
