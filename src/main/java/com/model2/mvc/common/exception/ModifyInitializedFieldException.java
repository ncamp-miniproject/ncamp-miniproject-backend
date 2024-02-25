package com.model2.mvc.common.exception;

public class ModifyInitializedFieldException extends RuntimeException {
    private static final long serialVersionUID = -1140338956925608877L;

    public ModifyInitializedFieldException() {
        super();
    }

    public ModifyInitializedFieldException(String msg) {
        super(msg);
    }
}
