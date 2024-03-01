package com.model2.mvc.common;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Setter {

    public static <E> List<E> get(String param, Function<String, E> func) {
        String[] parsed = param.split("_");
        
        List<E> got = new ArrayList<>();
        for (String p : parsed) {
            got.add(func.apply(p));
        }
        return got;
    }
}
