package com.model2.mvc.common.util;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class WebUtil {

    public static MultiValueMap<String, String> generateQueryParameterFrom(Object dto) {
        MultiValueMap<String, String> mvMap = new LinkedMultiValueMap<>();
        Method[] methods = dto.getClass().getDeclaredMethods();
        Arrays.stream(methods).filter(m -> m.getName().startsWith("get")).filter(m -> {
            try {
                return m.invoke(dto) != null;
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }).forEach(m -> {
            try {
                mvMap.add(toOgnl(m.getName()), m.invoke(dto).toString());
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
        return mvMap;
    }

    private static String toOgnl(String getterOrSetter) {
        final int detachLength = 3;
        return getterOrSetter.substring(detachLength, detachLength + 1).toLowerCase() +
               getterOrSetter.substring(detachLength + 1);
    }
}
