package com.model2.mvc.common.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class TestStringUtil {

    private static final String SEPARATOR = "-";

    private void testUnit(String base, String value, String expected) {
        String result = StringUtil.addValueWithoutDuplicate(base, value, SEPARATOR);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void addValueWithoutDuplicate_insertIntoNull() {
        testUnit(null, "asdf", "asdf");
    }

    @Test
    public void addValueWithoutDuplicate_insertIntoEmptyString() {
        testUnit("", "asdf", "asdf");
    }

    @Test
    public void addValueWithoutDuplicate_notContainsAlready() {
        testUnit("asdf", "fdsa", "fdsa" + SEPARATOR + "asdf");
    }

    @Test
    public void addValueWithoutDuplicate_tryInsertingDuplicate() {
        testUnit("asdf", "asdf", "asdf");
    }

    @Test
    public void addValueWithoutDuplicate_duplicateAppearsAtFirst() {
        testUnit("asdf" + SEPARATOR + "fdsa", "asdf", "asdf" + SEPARATOR + "fdsa");
    }

    @Test
    public void addValueWithoutDuplicate_duplicateAppearsAtTheMiddle() {
        testUnit("fdsa" + SEPARATOR + "asdf" + SEPARATOR + "gggg",
                 "asdf",
                 "asdf" + SEPARATOR + "fdsa" + SEPARATOR + "gggg");
    }

    @Test
    public void addValueWithoutDuplicate_duplicateAppearsAtTheEnd() {
        testUnit("fdsa" + SEPARATOR + "gggg" + SEPARATOR + "asdf",
                 "asdf",
                 "asdf" + SEPARATOR + "fdsa" + SEPARATOR + "gggg");
    }
}