package com.model2.mvc.framework;

import com.model2.mvc.common.exception.UnsupportedPathException;

import javax.servlet.ServletContext;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Deprecated
public class RequestMapping {

    private static RequestMapping requestMapping;
    private Map<String, Action> map;
    private Properties properties;
    private ServletContext servletContext;

    protected RequestMapping() {
    }

    private RequestMapping(String resources, ServletContext servletContext) {
        this.map = new HashMap<String, Action>();
        this.servletContext = servletContext;

        InputStream in = null;
        try {
            in = servletContext.getResourceAsStream(resources);
            this.properties = new Properties();
            properties.load(in);
        } catch (Exception ex) {
            System.out.println(ex);
            throw new RuntimeException("actionmapping.properties 파일 로딩 실패 :" + ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    public synchronized static RequestMapping getInstance(String resources, ServletContext servletContext) {
        if (requestMapping == null) {
            requestMapping = new WiringRequestMapping(resources, servletContext);
        }
        return requestMapping;
    }

    public Action getAction(String path) throws UnsupportedPathException {
        Action action = map.get(path);
        if (action == null) {
            if (!properties.containsKey(path)) {
                throw new UnsupportedPathException("path=" + path + " is not supported.");
            }

            String className = properties.getProperty(path);
            className = className.trim();

            try {
                synchronized (this) {
                    if (!this.map.containsKey(path)) {
                        Class<?> expectedToAction = Class.forName(className);
                        Action instantiated = (Action)expectedToAction.newInstance();
                        this.map.put(path, instantiated);
                        instantiated.setServletContext(this.servletContext);
                        instantiated.init();
                    }
                    action = this.map.get(path);
                }
            } catch (ClassCastException e) {
                System.err.println(className + " isn't instance of " + Action.class);
            } catch (ClassNotFoundException e) {
                System.err.println(className + " has not been defined");
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return action;
    }

    public void destroyAllActions() {
        this.map.values().forEach(action -> action.destroy());
        this.map.clear();
    }
}