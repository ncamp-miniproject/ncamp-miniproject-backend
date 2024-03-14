package com.model2.mvc.common.util.mail;

public class MailTransferException extends Exception {

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
