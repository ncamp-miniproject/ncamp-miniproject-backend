package com.model2.mvc.common.util.mail;

public class InternetAddressException extends MailTransferException {

    public InternetAddressException() {
        super();
    }

    public InternetAddressException(String message) {
        super(message);
    }

    public InternetAddressException(Exception e) {
        super(e);
    }
}
