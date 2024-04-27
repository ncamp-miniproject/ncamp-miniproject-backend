package com.model2.mvc.common.file;

public interface FileAccess {

    public String storeFile(byte[] data, String fileExtension, String path);

    public String storeFile(String base64Data, String fileExtension, String path);
}
