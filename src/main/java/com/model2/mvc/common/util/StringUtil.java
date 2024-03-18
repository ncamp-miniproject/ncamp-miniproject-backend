package com.model2.mvc.common.util;

import java.time.LocalDate;
import java.util.Arrays;

public class StringUtil {

    public static String null2nullStr(String nullable) {
        return nullable == null ? "" : nullable;
    }

    public static String addValueWithoutDuplicate(String into, String newValue, String separator) {
        if (into == null) {
            return addValueWithoutDuplicate("", newValue, separator);
        }
        if (into.isEmpty()) {
            return newValue;
        }
        int indexOfTarget = into.indexOf(newValue);
        if (indexOfTarget == -1) {
            return newValue + separator + into;
        }
        int indexOfNextSeparator = into.indexOf(separator, indexOfTarget);
        return indexOfNextSeparator == -1
               ? addValueWithoutDuplicate(into.substring(0, indexOfTarget == 0 ? 0 : indexOfTarget - 1), newValue, separator)
               : newValue + separator + into.substring(0, indexOfTarget) + into.substring(indexOfNextSeparator + 1);
    }

    public static LocalDate parseDate(String from, String separator) {
        if (from == null || from.isEmpty()) {
            return LocalDate.now();
        }
        int[] parsed = Arrays.stream(from.split(separator)).mapToInt(Integer::parseInt).toArray();
        return LocalDate.of(parsed[0], parsed[1], parsed[2]);
    }
}
