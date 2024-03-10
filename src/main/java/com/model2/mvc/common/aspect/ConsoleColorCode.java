package com.model2.mvc.common.aspect;

public enum ConsoleColorCode {
    RESET("\u001B[0m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    YELLOW("\u001B[33m"),
    GREEN("\u001B[32m");

    private String code;

    ConsoleColorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
