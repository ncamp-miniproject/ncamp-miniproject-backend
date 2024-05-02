package com.model2.mvc.config.context;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class PropertyConfig {

    @Bean
    public PropertiesFactoryBean javaMailConfig() {
        return getPropertiesFactoryBean("secret/mail-config.properties");
    }

    @Bean
    public PropertiesFactoryBean constantProperties() {
        return getPropertiesFactoryBean("constants/common-constants.properties");
    }

    private PropertiesFactoryBean getPropertiesFactoryBean(String propertiesLocation) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(propertiesLocation)) {
            PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
            Properties properties = new Properties();
            properties.load(is);
            propertiesFactoryBean.setProperties(properties);
            return propertiesFactoryBean;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
