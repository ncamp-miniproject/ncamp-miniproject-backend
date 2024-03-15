package com.model2.mvc.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

@NoArgsConstructor
@Getter
public class BasicJSONResponse {
    private int status;
    private String message;
    private Object data;

    public BasicJSONResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static ResponseEntity<BasicJSONResponse> ok(Object data) {
        return new ResponseEntity<>(from(HttpStatus.OK, data), HttpStatus.OK);
    }

    public static ResponseEntity<BasicJSONResponse> ok(Object data, MultiValueMap<String, String> headers) {
        return new ResponseEntity<>(from(HttpStatus.OK, data), headers, HttpStatus.OK);
    }

    public static ResponseEntity<BasicJSONResponse> created(Object data) {
        return new ResponseEntity<>(from(HttpStatus.CREATED, data), HttpStatus.CREATED);
    }

    public static ResponseEntity<BasicJSONResponse> created(Object data, MultiValueMap<String, String> headers) {
        return new ResponseEntity<>(from(HttpStatus.CREATED, data), headers, HttpStatus.CREATED);
    }

    public static ResponseEntity<BasicJSONResponse> noContent() {
        return new ResponseEntity<>(from(HttpStatus.NO_CONTENT, ""), HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<BasicJSONResponse> noContent(MultiValueMap<String, String> headers) {
        return new ResponseEntity<>(from(HttpStatus.NO_CONTENT, ""), headers, HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<BasicJSONResponse> seeOther(Object data) {
        return new ResponseEntity<>(from(HttpStatus.SEE_OTHER, data), HttpStatus.SEE_OTHER);
    }

    public static ResponseEntity<BasicJSONResponse> seeOther(Object data, MultiValueMap<String, String> headers) {
        return new ResponseEntity<>(from(HttpStatus.SEE_OTHER, data), headers, HttpStatus.SEE_OTHER);
    }

    private static BasicJSONResponse from(HttpStatus httpStatus, Object data) {
        return new BasicJSONResponse(httpStatus.value(), httpStatus.getReasonPhrase(), data);
    }
}
