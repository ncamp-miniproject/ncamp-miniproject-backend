package com.model2.mvc.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BeanUtil {

    public static <T> T doMapping(Class<T> typeToGenerate, Object... given)
    throws InstantiationException, IllegalAccessException {
        List<Method> getters = new LinkedList<>();
        Map<Method, Object> methodGivenMap = new HashMap<>();
        for (Object obj : given) {
            Method[] methods = obj.getClass().getMethods();
            getters.addAll(Arrays.asList(methods));
            for (Method method : methods) {
                getters.add(method);
                methodGivenMap.put(method, obj);
            }
        }
        Map<String, Method> givenMethodNameMap = new HashMap<>();
        getters.stream()
                .filter(m -> m.getName().startsWith("get"))
                .forEach(m -> givenMethodNameMap.put(toOgnl(m.getName()), m));

        T newInstance = typeToGenerate.newInstance();

        Arrays.stream(typeToGenerate.getDeclaredMethods())
                .filter(m -> m.getName().startsWith("set"))
                .forEach(m -> {
                    Method getter = givenMethodNameMap.get(toOgnl(m.getName()));
                    Object objOfMethod = methodGivenMap.get(getter);
                    if (getter == null || objOfMethod == null) {
                        return;
                    }
                    try {
                        m.invoke(newInstance, getter.invoke(objOfMethod));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                });
        return newInstance;
    }

    public static String toOgnl(String methodName) {
        if (methodName == null || methodName.isEmpty()) {
            return methodName;
        }

        String getOrSetRemoved = methodName.replaceFirst("^set|^get", "");
        char[] c = getOrSetRemoved.toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        return String.valueOf(c);
    }
}
