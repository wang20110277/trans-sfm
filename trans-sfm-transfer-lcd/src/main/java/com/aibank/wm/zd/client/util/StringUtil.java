package com.aibank.wm.zd.client.util;


import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static String stringSubstitute(final String input, final Map<String, Object> valueMap) {
        return StringSubstitutor.replace(input, valueMap, "{", "}");
    }

    public static Map<String, Object> extractVariables(final String input, final String template) {
        return extractVariables(input, template, "\\{", "\\}");
    }

    /**
     * @param input    /Volumes/data/tmp/send/20999999/sx/0000000110-0000000051-007-20211207-01.txt
     * @param template /{baseDir}/send/{yyyyMMdd}/{organization}/{sendOrganization}-{receiveOrganization}-{fileType}-{date}-{batch}.txt
     * @param prefix
     * @param suffix
     * @return
     */
    public static Map<String, Object> extractVariables(final String input, final String template, final String prefix, final String suffix) {
        final HashSet<String> variableNames = new HashSet<>();
        String variableNamesRegex = "(" + prefix + "([^" + prefix + suffix + "]+?)" + suffix + ")";
        Pattern variableNamesPattern = Pattern.compile(variableNamesRegex);
        Matcher variableNamesMatcher = variableNamesPattern.matcher(template);
        while (variableNamesMatcher.find()) {
            variableNames.add(variableNamesMatcher.group(2));
        }
        final String regexTemplate = template.replaceAll(prefix, "(?<").replaceAll(suffix, ">.*)");
        Map<String, Object> resultMap = new HashMap<>();
        Matcher matcher = Pattern.compile(regexTemplate).matcher(input);
        matcher.find();
        variableNames.forEach(v -> resultMap.put(v, matcher.group(v)));
        return resultMap;
    }

    public static boolean match(final String input, final String template) {
        return match(input, template, "\\{", "\\}");
    }

    public static boolean match(final String input, final String template, final String prefix, final String suffix) {
        try {
            extractVariables(input, template, prefix, suffix);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
