package com.model2.mvc.auth.service;

import com.model2.mvc.common.util.RandomSerialGenerator;
import com.model2.mvc.mail.MailAgent;
import com.model2.mvc.mail.MailTransferException;
import com.model2.mvc.auth.repository.RegisterAuthenticationRepository;
import com.model2.mvc.user.domain.MailAuthenticationInfo;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class MailRegisterAuthenticationService implements RegisterAuthenticationService {
    private final RegisterAuthenticationRepository registerAuthenticationRepository;
    private final MailAgent mailAgent;

    @Override
    public void sendAuthenticationRequest(String receiverMailAddress) throws MailTransferException {
        JSONObject metadata = readMailMetadata();
        String generatedCode = RandomSerialGenerator.generate(40);
        this.mailAgent.send(receiverMailAddress,
                            new java.util.Date(),
                            (String)metadata.get("subject"),
                            (String)metadata.get("message"),
                            (String)metadata.get("authenticationURL") +
                            generatedCode +
                            "&authenticatedEmail=" +
                            receiverMailAddress);
        MailAuthenticationInfo mailAuthenticationInfo = new MailAuthenticationInfo();
        mailAuthenticationInfo.setEmail(receiverMailAddress);
        mailAuthenticationInfo.setAuthenticationCode(generatedCode);
        this.registerAuthenticationRepository.save(mailAuthenticationInfo);
    }

    private JSONObject readMailMetadata() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("constants/authenticate-mail.json");
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return (JSONObject)JSONValue.parse(sb.toString());
    }

    @Override
    public boolean checkValidCode(String email, String code) {
        Optional<MailAuthenticationInfo> byEmail = this.registerAuthenticationRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            MailAuthenticationInfo info = byEmail.get();
            if (info.getAuthenticationCode().equals(code)) {
                System.out.println("Equals");
                info.setAuthenticated(true);
                return true;
            } else {
                System.out.println("Not Equals");
            }
        }
        return false;
    }

    @Override
    public boolean checkAuthentication(String email) {
        Optional<MailAuthenticationInfo> infoOptional = this.registerAuthenticationRepository.findByEmail(email);
        if (infoOptional.isPresent()) {
            MailAuthenticationInfo info = infoOptional.get();
            return info.isAuthenticated();
        }
        return false;
    }

    @Override
    public void removeAuthenticationInfo(String email) {
        this.registerAuthenticationRepository.deleteByEmail(email);
    }
}
