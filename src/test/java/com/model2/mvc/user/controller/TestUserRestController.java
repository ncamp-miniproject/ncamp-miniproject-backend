package com.model2.mvc.user.controller;

import static org.assertj.core.api.Assertions.*;

import com.model2.mvc.common.dto.BasicJSONResponse;
import com.model2.mvc.user.domain.User;
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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class TestUserRestController {
    private URI basicURI = new URIBuilder().setScheme("http").setHost("localhost").setPort(8089).build();

    private static ObjectMapper objectMapper = new ObjectMapper();

    public TestUserRestController() throws URISyntaxException {
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
            String testCase = readTestCaseBody(resourceName + ".json");
            HttpEntity requestEntity = new StringEntity(testCase);
            ((HttpPost)requestBase).setEntity(requestEntity);
        }

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(requestBase)) {
            HttpEntity responseEntity = response.getEntity();

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
    public void createUser() throws Exception {
        URI uri = new URIBuilder(basicURI).setPath("/app/users/account/new").build();

        System.out.println(uri);

        String result = getRequestResult(uri, "create-user", "post");

        URI deleteURI = new URIBuilder(basicURI).setPath("/app/users/account/user0001/delete").build();

        System.out.println(result);

        String deleteResult = getRequestResult(deleteURI, null, "post");

        System.out.println(deleteResult);

        BasicJSONResponse result1 = objectMapper.readValue(result, BasicJSONResponse.class);

        Map<String, String> resultUser = (Map<String, String>)result1.getData();

        BasicJSONResponse result2 = objectMapper.readValue(deleteResult, BasicJSONResponse.class);

        Map<String, String> removedUser = (Map<String, String>)result2.getData();

        System.out.println(resultUser);
        System.out.println(removedUser);

        assertThat(removedUser.get("userId")).isEqualTo(resultUser.get("userId"));
    }
}