package com.model2.mvc.mail;

import com.model2.mvc.auth.exception.AuthRequestFailException;

public class MailTransferException extends AuthRequestFailException {

    public MailTransferException() {
        super();
    }

    public MailTransferException(String message) {
        super(message);
    }

    public MailTransferException(Exception e) {
        super(e);
    }
}
