package com.model2.mvc.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestListPaginationUtil {

    private static class TestCase {
        int dataCount;
        int currentPage;
        int pageSize;
        int pageSetDisplay;
        List<Integer> expectedSize;

        TestCase(int dataCount, int currentPage, int pageSize, int pageSetDisplay, List<Integer> expectedSize) {
            this.dataCount = dataCount;
            this.currentPage = currentPage;
            this.pageSize = pageSize;
            this.pageSetDisplay = pageSetDisplay;
            this.expectedSize = expectedSize;
        }
    }

    static Stream<TestCase> testGetPageSetValues() {
        int pageSize = 10;
        int pageSetDisplay = 10;
        TestCase[] testCases = {
                new TestCase(0, 1, pageSize, pageSetDisplay, new ArrayList<>()),
                new TestCase(0, 3, pageSize, pageSetDisplay, new ArrayList<>()),
                new TestCase(300, 30, pageSize, pageSetDisplay, Arrays.asList(21, 22, 23, 24, 25, 26, 27, 28, 29, 30)),
                new TestCase(307, 30, pageSize, pageSetDisplay, Arrays.asList(21, 22, 23, 24, 25, 26, 27, 28, 29, 30)),
                new TestCase(307, 31, pageSize, pageSetDisplay, Arrays.asList(31)),
                new TestCase(307, 35, pageSize, pageSetDisplay, Arrays.asList(31)),
                new TestCase(307, 40, pageSize, pageSetDisplay, Arrays.asList(31)),
                new TestCase(307, 41, pageSize, pageSetDisplay, Arrays.asList()),
                new TestCase(37, 4, pageSize, pageSetDisplay, Arrays.asList(1, 2, 3, 4)),
                new TestCase(7, 1, pageSize, pageSetDisplay, Arrays.asList(1)),
                new TestCase(13, 1, pageSize, pageSetDisplay, Arrays.asList(1, 2)),
                new TestCase(13, 2, pageSize, pageSetDisplay, Arrays.asList(1, 2)),
                new TestCase(108, 11, 3, 10, Arrays.asList(11, 12, 13, 14, 15, 16, 17, 18, 19, 20))
        };

        return Stream.of(testCases);
    }

    static int i = 1;

    @Test
    public void testGetPageSet() {
        testGetPageSetValues().forEach(testCase -> {
            int dataCount = testCase.dataCount;
            int currentPage = testCase.currentPage;
            int pageSize = testCase.pageSize;
            int pageSetDisplay = testCase.pageSetDisplay;
            List<Integer> expected = testCase.expectedSize;

            List<Integer> result = ListPageUtil.getPageSet(dataCount, currentPage, pageSize, pageSetDisplay);

            System.out.println("------- " + i++ + " --------");
            System.out.println("dataCount=" + dataCount);
            System.out.println("currentPage=" + currentPage);
            System.out.println("pageSize=" + pageSize);
            System.out.println("pageSetDisplay=" + pageSetDisplay + "\n");
            System.out.println("expected=" + expected);
            System.out.println("result=" + result);

            assertThat(result.size()).isEqualTo(expected.size());
            assertThat(result).isEqualTo(expected);
        });
    }
}
