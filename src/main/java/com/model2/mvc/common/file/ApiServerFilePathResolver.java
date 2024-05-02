package com.model2.mvc.common.file;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class ApiServerFilePathResolver implements FilePathResolver {
    private static final String WHERE = "https://api.novemberalpha.shop";
    private static final String CONTEXT_IMAGE_PATH = "/images/uploadFiles/";

    @Override
    public String resolve(String filename) {
        return WHERE + CONTEXT_IMAGE_PATH + filename;
    }
}
