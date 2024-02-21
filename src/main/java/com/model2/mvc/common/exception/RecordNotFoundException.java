package com.model2.mvc.common.exception;

public class RecordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 5186344328908920147L;

    public RecordNotFoundException() {
        super();
    }
    
    public RecordNotFoundException(String msg) {
        super(msg);
    }
}
