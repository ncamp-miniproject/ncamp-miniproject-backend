package com.model2.mvc.common.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestIntBitwiseUtil {
    
    @CsvSource(value = { "0:0", "1:1", "2:1", "3:1", "4:1", "30:1", "-1:1", "-2:1" }, delimiter = ':')
    @ParameterizedTest
    void testGetOneIfNotZero(String input, String expected) {
        int inputValue = Integer.parseInt(input);
        int expectedValue = Integer.parseInt(expected);
        
        assertThat(IntBitwiseUtil.getOneIfNotZero(inputValue)).isEqualTo(expectedValue);
    }
    
    @CsvSource(value = { "0:0", "1:1", "2:2", "3:3", "12823:12823", "-1:0", "-2:0", "-134194824:0" }, delimiter = ':')
    @ParameterizedTest
    void testConvertNegativeToZero(String input, String expected) {
        int inputVal = Integer.parseInt(input);
        int expectedVal = Integer.parseInt(expected);
        
        assertThat(IntBitwiseUtil.convertNegativeToZero(inputVal)).isEqualByComparingTo(expectedVal);
    }
}
