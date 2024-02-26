package com.model2.mvc.common.util;

import java.sql.Date;
import java.util.Arrays;

public class StringUtil {

    public static String null2nullStr(String nullable) {
        return nullable == null ? "" : nullable;
    }

    public static String addValueWithoutDuplicate(String to, String newValue, String separator) {
        int indexOfTarget = to.indexOf(newValue);
        if (indexOfTarget == -1) {
            return newValue + "-" + to;
        }
        int indexOfNextSeparator = to.indexOf(separator, indexOfTarget);
        return indexOfNextSeparator == -1
               ? newValue + "-" + to.substring(0, indexOfTarget - 1 < 0 ? 0 : indexOfTarget)
               : newValue + "-" + to.substring(0, indexOfTarget) + to.substring(indexOfNextSeparator + 1);
    }

    public static Date parseDate(String from, String separator) {
        int[] parsed = Arrays.stream(from.split(separator)).mapToInt(Integer::parseInt).toArray();
        return new Date(parsed[0], parsed[1], parsed[2]);
    }
}
