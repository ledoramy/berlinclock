package com.ubs.opsit.interviews.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by dmitri on 29.06.16.
 */
public class ParseUtilTest {

    @Test
    public void testGetEntriesListByPattern() {
        String text = "22-35-00";
        List<String> expected = new ArrayList<String>() {{
            add("22");
            add("35");
            add("00");
        }};
        assertThat(expected, is(equalTo(ParseUtil.getEntriesListByPattern(text, Pattern.compile("(\\d{1,2})")))));
    }

    @Test
    public void testGetEntriesListByPatternWithSingleNumber() {
        String text = "2:32:1";
        List<String> expected = new ArrayList<String>() {{
            add("2");
            add("32");
            add("1");
        }};
        assertThat(expected, is(equalTo(ParseUtil.getEntriesListByPattern(text, Pattern.compile("(\\d{1,2})")))));
    }

    @Test
    public void testGetEntriesListByPatternWithGroup() {
        String text = "22:15-59";
        List<String> expected = new ArrayList<String>() {{
            add("2");
            add("5");
            add("9");
        }};
        assertThat(expected, is(equalTo(ParseUtil.getEntriesListByPattern(text, Pattern.compile("(\\d(\\d))"), 2))));
    }

}