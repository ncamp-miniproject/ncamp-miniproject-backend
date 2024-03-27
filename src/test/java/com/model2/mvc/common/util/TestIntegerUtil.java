package com.model2.mvc.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TestIntegerUtil {
    
    @Test
    public void testGetOneIfNotZero() {
        int[] inputs = {
                0, 1, 2, 3, 4, 30, -1, -2
        };
        int[] expected = {
                0, 1, 1, 1, 1, 1, 1, 1
        };

        for (int i = 0; i < inputs.length; i++) {
            testGetOneIfNotZero(inputs[i], expected[i]);
        }
    }

    private void testGetOneIfNotZero(int input, int expected) {
        assertThat(IntegerUtil.getOneIfNotZero(input)).isEqualTo(expected);
    }
    
    @Test
    public void testConvertNegativeToZero() {
        int[] inputs = {
                0, 1, 2, 3, 12823, -1, -2, -134194824
        };
        int[] expected = {
                0, 1, 2, 3, 12823, 0, 0, 0
        };

        for (int i = 0; i < inputs.length; i++) {
            testConvertNegativeToZero(inputs[i], expected[i]);
        }
    }

    private void testConvertNegativeToZero(int input, int expected) {
        assertThat(IntegerUtil.convertNegativeToZero(input)).isEqualByComparingTo(expected);
    }
}
