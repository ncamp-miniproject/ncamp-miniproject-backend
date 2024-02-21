package com.model2.mvc.common.exception;

import javax.servlet.ServletException;

public class UnsupportedPathException extends ServletException {
    private static final long serialVersionUID = -5704227055566968686L;
    
    public UnsupportedPathException() {
        super();
    }

    public UnsupportedPathException(String message) {
        super(message);
    }
}
