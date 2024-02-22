package com.model2.mvc.common.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import javax.servlet.ServletContext;

public class SQLContainer {
    private static final Map<String, String> container = new HashMap<>();
    
    public static void init(String resource, ServletContext servletContext) {
        System.out.println("resource: " + resource);
        Properties properties = new Properties();
        try (InputStream is = servletContext.getResourceAsStream(resource)) {
            properties.load(is);
           
            properties.stringPropertyNames().forEach(k -> {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(servletContext.getResourceAsStream(properties.getProperty(k))))) {
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    container.put(k, sb.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for (String key : container.keySet()) {
            System.out.println("[" + key + "]");
            System.out.println(container.get(key));
            System.out.println("----------------------");
        }
    }
    
    public static Optional<String> get(String name) {
        return Optional.ofNullable(container.get(name));
    }
}
