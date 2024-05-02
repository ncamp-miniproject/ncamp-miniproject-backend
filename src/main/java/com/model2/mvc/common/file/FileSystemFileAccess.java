package com.model2.mvc.common.file;

import com.model2.mvc.common.util.RandomSerialGenerator;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;

@Component
public class FileSystemFileAccess implements FileAccess {
    private static final int RANDOM_FILENAME_LENGTH = 30;

    @Override
    public String storeFile(byte[] data, String fileExtension, String path) {
        String filename = RandomSerialGenerator.generate(RANDOM_FILENAME_LENGTH) + "." + fileExtension;
        String uploadPath = path + File.separator + filename;

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(uploadPath))) {
            bos.write(data);
        } catch (FileNotFoundException e) {
            File file = new File(path);
            file.mkdirs();
            return storeFile(data, fileExtension, path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return filename;
    }

    @Override
    public String storeFile(String base64Data, String fileExtension, String path) {
        byte[] decodedData = Base64.getDecoder().decode(base64Data);
        return storeFile(decodedData, fileExtension, path);
    }
}
