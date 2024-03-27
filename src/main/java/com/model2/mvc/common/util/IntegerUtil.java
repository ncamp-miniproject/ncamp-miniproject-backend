package com.model2.mvc.common.util;

public class IntegerUtil {

    public static int getOneIfNotZero(int value) {
        value = value & Integer.MAX_VALUE;
        value = (value | (value >> 16)) & 0b1111111111111111;
        value = (value | (value >> 8)) & 0b11111111;
        value = (value | (value >> 4)) & 0b1111;
        value = (value | (value >> 2)) & 0b11;
        value = (value | (value >> 1)) & 0b1;
        return value;
    }

    public static int convertNegativeToZero(int value) {
        int signBit = value & Integer.MIN_VALUE;
        int signBitInversion = nand(signBit, Integer.MIN_VALUE);
        int extension = signBitInversion >> 31;
        return value & extension;
    }

    public static int nand(int val1, int val2) {
        return (~val1 & val2) | (val1 & ~val2);
    }

    public static int getOneIfNull(Integer number) {
        return number == null ? 1 : number;
    }
}
