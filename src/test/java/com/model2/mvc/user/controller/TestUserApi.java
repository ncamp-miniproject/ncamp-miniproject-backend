package com.model2.mvc.user.controller;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class TestUserApi {
    private URI basicURI = new URIBuilder().setScheme("http").setHost("localhost").setPort(8089).build();

    private static ObjectMapper objectMapper = new ObjectMapper();

    public TestUserApi() throws URISyntaxException {
    }

    private String readTestCaseBody(String resourceName) throws Exception {
        InputStream resourceAsStream = getClass().getClassLoader()
                .getResourceAsStream("api-test-case/user/" + resourceName);

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }

    private HttpRequestBase getRequestBase(URI uri, String getOrPost) {
        HttpRequestBase requestBase;
        if (getOrPost.equals("post")) {
            requestBase = new HttpPost();
        } else {
            requestBase = new HttpGet();
        }
        requestBase.setURI(uri);
        requestBase.setHeader(new BasicHeader("Accept", "application/json"));
        requestBase.setHeader(new BasicHeader("Content-Type", "application/json"));
        return requestBase;
    }

    private String getRequestResult(URI uri, String resourceName, String getOrPost) throws Exception {
        getOrPost = getOrPost.trim().toLowerCase();
        HttpRequestBase requestBase = getRequestBase(uri, getOrPost);
        if (resourceName != null && getOrPost.equals("post")) {
            String testCase = readTestCaseBody(resourceName);
            HttpEntity requestEntity = new StringEntity(testCase);
            ((HttpPost)requestBase).setEntity(requestEntity);
        }

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(requestBase)) {
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity == null) {
                return null;
            }

            StringBuilder sb = new StringBuilder();
            String line;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(responseEntity.getContent(), "euc-kr"))) {
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }
            return sb.toString();
        }
    }

    @Test
    public void validateAuthentication_invokeForbidden() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URIBuilder().setScheme("http")
                .setHost("localhost")
                .setPort(8089)
                .setPath("/app/users/account/authentication")
                .addParameter("code", "123")
                .build();
        RequestEntity<String> requestEntity = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON).body("");
        for (int i = 0; i < 5; i++) {
            try {
                restTemplate.exchange(requestEntity, Void.class);
            } catch (HttpClientErrorException.Forbidden e) {
                System.out.println(e.getStatusCode());
                System.out.println(e.getMessage());
                System.out.println(e.getResponseBodyAsString());
            }
        }
    }
}