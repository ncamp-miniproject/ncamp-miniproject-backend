package com.model2.mvc.user.auth.service;

import com.model2.mvc.user.auth.exception.AuthRequestFailException;

public interface RegisterAuthenticationService {

    public void sendAuthenticationRequest(String subject) throws AuthRequestFailException;

    public boolean checkValidCode(String subject, String code);

    public boolean checkAuthentication(String subject);

    public void removeAuthenticationInfo(String subject);
}
