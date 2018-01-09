/* 
 * Copyright © 2017 aicloud
 */

/*
 * 修订记录:
 * @author aicloud 2017-08-04 13:42 创建
 */
package com.aicloud.common.util.tostring.format;

/**
 * 掩码工具
 */
public class MaskUtils {
    // 默认掩码字符
    private static final char DEFAULT_MASK_CHAR = '*';

    /**
     * 掩码
     *
     * @param str       需被掩码的字符串
     * @param startSize 前段明文长度
     * @param endSize   末段明文长度
     * @return 掩码后的字符串
     * @throws IllegalArgumentException startSize小于0或endSize小于0
     */
    public static String mask(String str, int startSize, int endSize) {
        return mask(str, startSize, endSize, DEFAULT_MASK_CHAR);
    }

    /**
     * 掩码
     *
     * @param str       需被掩码的字符串
     * @param startSize 前段明文长度
     * @param endSize   末段明文长度
     * @param maskChar  掩码字符
     * @return 掩码后的字符串
     * @throws IllegalArgumentException startSize小于0或endSize小于0
     */
    public static String mask(String str, int startSize, int endSize, char maskChar) {
        if (startSize < 0 || endSize < 0) {
            throw new IllegalArgumentException("startSize和endSize不能小于0");
        }
        if (str == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(str.length());
        // 构造前段明文
        if (startSize > str.length()) {
            startSize = str.length();
        }
        builder.append(str.substring(0, startSize));
        // 构造中段掩码
        int maskEndIndex = str.length() - endSize;
        maskEndIndex = maskEndIndex < startSize ? startSize : maskEndIndex;
        for (int i = startSize; i < maskEndIndex; i++) {
            builder.append(maskChar);
        }
        // 构造末段明文
        builder.append(str.substring(maskEndIndex));

        return builder.toString();
    }
}
