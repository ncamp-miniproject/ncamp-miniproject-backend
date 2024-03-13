package com.model2.mvc.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomSerialGenerator {
    private static final List<Character> asciiSet = new ArrayList<>();

    static {
        for (int i = '0'; i <= '9'; i++) {
            asciiSet.add((char)i);
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            asciiSet.add((char)i);
        }
        for (int i = 'a'; i <= 'z'; i++) {
            asciiSet.add((char)i);
        }
    }

    public static final int DEFAULT_LENGTH = 25;

    public static String generate() {
        Random random = new Random();

        char[] serial = new char[DEFAULT_LENGTH];
        int size = asciiSet.size();
        for (int i = 0; i < serial.length; i++) {
            serial[i] = asciiSet.get(Math.abs(random.nextInt()) % size);
        }
        return String.valueOf(serial);
    }
}
