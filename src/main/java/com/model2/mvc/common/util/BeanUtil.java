package com.model2.mvc.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {

    public static <T> T generateGiven(Class<T> typeToGenerate, Object given)
    throws InstantiationException, IllegalAccessException {
        Method[] methods = given.getClass().getDeclaredMethods();
        Map<String, Method> givenMethodNameMap = new HashMap<>();
        Arrays.stream(methods)
                .filter(m -> m.getName().startsWith("get"))
                .forEach(m -> givenMethodNameMap.put(toOgnl(m.getName()), m));

        T newInstance = typeToGenerate.newInstance();

        Arrays.stream(typeToGenerate.getDeclaredMethods())
                .filter(m -> m.getName().startsWith("set"))
                .forEach(m -> {
                    Method getter = givenMethodNameMap.get(toOgnl(m.getName()));
                    if (getter == null) {
                        return;
                    }
                    try {
                        m.invoke(newInstance, getter.invoke(given));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                });
        return newInstance;
    }

    private static boolean isGetterOrSetter(String methodName) {
        return methodName.startsWith("get") || methodName.startsWith("set");
    }

    private static String toOgnl(String methodName) {
        if (methodName == null || methodName.isEmpty()) {
            return methodName;
        }

        String getOrSetRemoved = methodName.replaceFirst("^set|^get", "");
        char[] c = getOrSetRemoved.toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        return String.valueOf(c);
    }
}
