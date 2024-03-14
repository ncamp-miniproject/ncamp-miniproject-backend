package com.model2.mvc.common.util.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

@Component
@Primary
public class SpringMimeMessageMailAgent implements MailAgent {

    @Value("#{javaMailConfig['username']}")
    private String username;

    @Value("#{javaMailConfig['password']}")
    private String password;

    @Value("#{javaMailConfig['smtpHost']}")
    private String host;

    @Value("#{javaMailConfig['starttls']}")
    private String starttls;

    @Value("#{javaMailConfig['auth']}")
    private String auth;

    @Value("#{javaMailConfig['debug']}")
    private Boolean debug;

    @Value("#{javaMailConfig['port']}")
    private String port;

    public SpringMimeMessageMailAgent() {
    }

    @Override
    public void send(String recipient, Date sentDate, String subject, String... messages) throws MailTransferException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.starttls.enable", starttls);
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.debug", debug);
        properties.put("mail.smtp.port", port);

        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            InternetAddress recipientAddress = new InternetAddress(recipient);
            message.setRecipient(Message.RecipientType.TO, recipientAddress);
            message.setSubject(subject);
            message.setSentDate(sentDate);

            Multipart multipart = new MimeMultipart();
            for (String messageBody : messages) {
                BodyPart bodyPart = new MimeBodyPart();
                bodyPart.setText(messageBody);
                multipart.addBodyPart(bodyPart);
            }
            message.setContent(multipart);

            Transport.send(message);
        } catch (AddressException e) {
            throw new InternetAddressException(e);
        } catch (MessagingException e) {
            throw new MailTransferException(e);
        }
    }
}
