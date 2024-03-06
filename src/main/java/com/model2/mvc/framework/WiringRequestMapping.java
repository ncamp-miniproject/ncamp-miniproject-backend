package com.model2.mvc.framework;

import com.model2.mvc.common.exception.UnsupportedPathException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class WiringRequestMapping extends RequestMapping {
    private Properties urlBeanMapping;
    private ServletContext servletContext;
    private ApplicationContext applicationContext;


    protected WiringRequestMapping(String resources, ServletContext servletContext) {
        super();
        this.servletContext = servletContext;
        this.urlBeanMapping = new Properties();

        InputStream in = null;
        try {
            in = servletContext.getResourceAsStream(resources);
            this.urlBeanMapping.load(in);
        } catch (Exception ex) {
            System.out.println(ex);
            this.urlBeanMapping = null;
            throw new RuntimeException("actionmapping.properties 파일 로딩 실패 :" + ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception ignored) {
                }
            }
        }

        this.applicationContext = new ClassPathXmlApplicationContext("classpath:spring-config/common.xml",
                                                                     "classpath:spring-config/context-aspect.xml",
                                                                     "classpath:spring-config/context-mybatis.xml",
                                                                     "classpath:spring-config/context-transaction.xml");
    }

    @Override
    public Action getAction(String path) throws UnsupportedPathException {
        if (!this.urlBeanMapping.containsKey(path)) {
            throw new UnsupportedPathException("path=" + path + " is not supported.");
        }

        String beanName = this.urlBeanMapping.getProperty(path).trim();
        return this.applicationContext.getBean(beanName, Action.class);
    }
}
