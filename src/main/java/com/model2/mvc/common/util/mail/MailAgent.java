package com.model2.mvc.common.util.mail;

import java.util.Date;

public interface MailAgent {

    public void send(String recipient, Date sentDate, String subject, String... messages) throws MailTransferException;
}
