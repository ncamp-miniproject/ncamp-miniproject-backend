package com.model2.mvc.common.util;

public class StringUtil {

    public static String null2nullStr(String nullable) {
        return nullable == null ? "" : nullable;
    }

    public static String addValueWithoutDuplicate(String to,
                                                  String newValue,
                                                  String separator) {
        int indexOfTarget = to.indexOf(newValue);
        if (indexOfTarget == -1) {
            return newValue + "-" + to;
        }
        int indexOfNextSeparator = to.indexOf(separator, indexOfTarget);
        return indexOfNextSeparator == -1
                ? newValue + "-" + to.substring(0, indexOfTarget - 1)
                : newValue + "-" + to.substring(0, indexOfTarget) +
                  to.substring(indexOfNextSeparator + 1);
    }
}
