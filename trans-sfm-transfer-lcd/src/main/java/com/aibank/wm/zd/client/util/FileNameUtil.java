package com.aibank.wm.zd.client.util;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.aibank.wm.zd.client.pojo.FileNameVariables;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.text.StringSubstitutor;

import java.util.*;

import static com.aibank.wm.zd.client.constants.CommonConstant.OK_FILE_SUFFIX;


/**
 * {sendOrganization}-{receiveOrganization}-{fileType}-{reconDate}-{batch}.txt",
 * COUNT-{sendOrganization}-{receiveOrganization}-{fileType}-{reconDate}.txt
 */
public class FileNameUtil {
    private static final String COUNT_FILE_PREFIX = "COUNT-";
    private static final String COUNT_FILE_TYPE = "099";
    private static final String COUNT_FILE_TEMPLATES = "COUNT-{sendOrganization}-{receiveOrganization}-{fileType}-{reconDate}.txt";
    private static final String COMMON_FILE_TEMPLATES = "{sendOrganization}-{receiveOrganization}-{fileType}-{reconDate}-{batch}.txt";

    public static Map<String, Object> extractVariables(final String input, final String template) {
        return StringUtil.extractVariables(input, template, "\\{", "\\}");
    }

    public static String stringSubstitute(final String input, final Map<String, Object> valueMap) {
        return StringSubstitutor.replace(input, valueMap, "{", "}");
    }


    public static String stringSubstitute(final String input, FileNameVariables fileNameVariables) {
        return FileNameUtil.stringSubstitute(input, BeanUtil.beanToMap(fileNameVariables));
    }


    private static String getFileTemplates(String input) {
        if (input.startsWith(COUNT_FILE_PREFIX)) {
            return COUNT_FILE_TEMPLATES;
        } else {
            return COMMON_FILE_TEMPLATES;
        }
    }

    public static FileNameVariables extractVariables(final String input) {
        String template = getFileTemplates(input);
        try {
            Map<String, Object> variables = StringUtil.extractVariables(input, template);
            final FileNameVariables fileNameVariables = BeanUtil.mapToBean(variables, FileNameVariables.class, false, CopyOptions.create().ignoreError());
            fileNameVariables.setFileName(input);

            // COUNT 文件,文件名中的 fileType 是对应的业务文件的类型,需要改成计数文件的类型
            if (input.startsWith(COUNT_FILE_PREFIX)) {
                fileNameVariables.setFileType(COUNT_FILE_TYPE);
            }

            return fileNameVariables;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean match(final String input) {
        String template = getFileTemplates(input);
        return StringUtil.match(input, template);
    }

    public static boolean okFileMatch(final String input) {
        String template = getFileTemplates(input);
        return StringUtil.match(input, template + OK_FILE_SUFFIX);
    }
}
