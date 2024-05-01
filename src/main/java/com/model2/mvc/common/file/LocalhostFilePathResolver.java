package com.model2.mvc.common.file;

import org.springframework.stereotype.Component;

@Component
public class LocalhostFilePathResolver implements FilePathResolver {
    private static final String LOCALHOST = "http://localhost:8089";
    private static final String CONTEXT_IMAGE_PATH = "/images/uploadFiles/";

    @Override
    public String resolve(String filename) {
        return LOCALHOST + CONTEXT_IMAGE_PATH + filename;
    }
}
