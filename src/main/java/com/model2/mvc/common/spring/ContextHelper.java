package com.model2.mvc.common.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextHelper {
    private static ClassPathXmlApplicationContext applicationContext;
    private static Object lock = new Object();

    public static ApplicationContext getApplicationContext(String... configLocation) {
        if (applicationContext == null) {
            synchronized (lock) {
                if (applicationContext != null) {
                    return applicationContext;
                }
                applicationContext = new ClassPathXmlApplicationContext(configLocation);
            }
        }
        return applicationContext;
    }
}
