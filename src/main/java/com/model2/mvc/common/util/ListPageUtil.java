package com.model2.mvc.common.util;

import static com.model2.mvc.common.util.IntBitwiseUtil.convertNegativeToZero;
import static com.model2.mvc.common.util.IntBitwiseUtil.getOneIfNotZero;

import java.util.LinkedList;
import java.util.List;

public class ListPageUtil {

    public static List<Integer> getPageSet(int dataCount, int currentPage, int pageSize, int pageSetDisplay) {
        int pageCount = (dataCount - 1) / pageSize + getOneIfNotZero(dataCount);

        int currentPageSetIndex = (currentPage - 1) / pageSetDisplay + getOneIfNotZero(currentPage);
        int maxPageSet = (pageCount - 1) / pageSetDisplay + getOneIfNotZero(pageCount);
        int maxPageSetAndCurrentPageSetIndexDiff = maxPageSet - currentPageSetIndex;
        int zeroIfPageSetDiffNotPositive = convertNegativeToZero(maxPageSetAndCurrentPageSetIndexDiff);

        int displayMaxPageSet = maxPageSetAndCurrentPageSetIndexDiff + 1;
        displayMaxPageSet = convertNegativeToZero(displayMaxPageSet);
        displayMaxPageSet = getOneIfNotZero(displayMaxPageSet);
        int pageCountAtLastSet = ((pageCount - 1) % pageSetDisplay + 1) * displayMaxPageSet;
        int abbrPageCount = Math.abs(pageSetDisplay - pageCountAtLastSet);

        int pageCountToDisplay = pageCountAtLastSet + (abbrPageCount * getOneIfNotZero(zeroIfPageSetDiffNotPositive));

        List<Integer> pages = new LinkedList<>();
        for (int i = 0; i < pageCountToDisplay; i++) {
            pages.add(pageSetDisplay * (currentPageSetIndex - 1) + (i + 1));
        }
        return pages;
    }

    public static boolean isPreviousPageSetAvailable(int dataCount, int currentPage, int pageSize, int pageSetDisplay) {
        return currentPage > pageSetDisplay;
    }

    public static boolean isNextPageSetAvailable(int dataCount, int currentPage, int pageSize, int pageSetDisplay) {
        int pageCount = (dataCount - 1) / pageSize + getOneIfNotZero(dataCount);
        int currentPageSetIndex = (currentPage - 1) / pageSetDisplay + getOneIfNotZero(currentPage);
        int maxPageSet = (pageCount - 1) / pageSetDisplay + getOneIfNotZero(pageCount);
        int maxPageSetAndCurrentPageSetIndexDiff = maxPageSet - currentPageSetIndex;
        int zeroIfPageSetDiffNotPositive = convertNegativeToZero(maxPageSetAndCurrentPageSetIndexDiff);
        return zeroIfPageSetDiffNotPositive != 0;
    }

    public static int getPreviousPageSetEntry(int currentPage, int pageSetDisplay) {
        return (currentPage - 1) / pageSetDisplay * pageSetDisplay;
    }

    public static int getNextPageSetEntry(int currentPage, int pageSetDisplay) {
        int currentPageSetIndex = (currentPage - 1) / pageSetDisplay + getOneIfNotZero(currentPage);
        return currentPageSetIndex * pageSetDisplay + 1;
    }
}
