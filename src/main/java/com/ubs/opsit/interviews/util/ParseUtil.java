package com.ubs.opsit.interviews.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dmitri on 29.06.16.
 */
public class ParseUtil {

    public static List<String> getEntriesListByPattern(String text, Pattern pattern) {
        return getEntriesListByPattern(text, pattern, 1);
    }

    public static List<String> getEntriesListByPattern(String text, Pattern pattern, int group) {
        List<String> resultList = new ArrayList<>();
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            resultList.add(matcher.group(group));
        }
        return resultList;
    }
}
