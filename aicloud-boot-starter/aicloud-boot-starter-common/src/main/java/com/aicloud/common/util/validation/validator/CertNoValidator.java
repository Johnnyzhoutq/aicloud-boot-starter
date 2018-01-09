/* 
 * Copyright © 2017 aicloud
 */

/*
 * 修订记录:
 * @author aicloud 2017-08-04 13:42 创建
 */
package com.aicloud.common.util.validation.validator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 身份证号校验器
 */
public class CertNoValidator {
    // 15位身份证号正则表达式
    private static final Pattern LEAP_YEAR_15_PATTERN = Pattern.compile("^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$");
    private static final Pattern NORMAL_YEAR_15_PATTERN = Pattern.compile("^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$");

    // 18位身份证号正则表达式
    private static final Pattern LEAP_YEAR_18_PATTERN = Pattern.compile("^[1-9][0-9]{5}[1-9][0-9]{3}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$");
    private static final Pattern NORMAL_YEAR_18_PATTERN = Pattern.compile("^[1-9][0-9]{5}[1-9][0-9]{3}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$");

    // 18位身份证号校验位校验信息
    private static final int[] VERIFY_FACTOR = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private static final String VERIFY_CODES = "10X98765432";

    /**
     * 身份证号前两位与地区映射map
     */
    public static final Map<String, String> CERT_NO_AREA_MAPPING;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("11", "北京");
        map.put("12", "天津");
        map.put("13", "河北");
        map.put("14", "山西");
        map.put("15", "内蒙古");
        map.put("21", "辽宁");
        map.put("22", "吉林");
        map.put("23", "黑龙江");
        map.put("31", "上海");
        map.put("32", "江苏");
        map.put("33", "浙江");
        map.put("34", "安徽");
        map.put("35", "福建");
        map.put("36", "江西");
        map.put("37", "山东");
        map.put("41", "河南");
        map.put("42", "湖北");
        map.put("43", "湖南");
        map.put("44", "广东");
        map.put("45", "广西");
        map.put("46", "海南");
        map.put("50", "重庆");
        map.put("51", "四川");
        map.put("52", "贵州");
        map.put("53", "云南");
        map.put("54", "西藏");
        map.put("61", "陕西");
        map.put("62", "甘肃");
        map.put("63", "青海");
        map.put("64", "宁夏");
        map.put("65", "新疆");
        map.put("71", "台湾");
        map.put("81", "香港");
        map.put("82", "澳门");
        map.put("91", "国外");

        CERT_NO_AREA_MAPPING = Collections.unmodifiableMap(map);
    }

    /**
     * 校验身份证号是否合法
     *
     * @param certNo 待校验身份证号
     * @return true 校验通过，false 校验未通过
     */
    public static boolean validate(String certNo) {
        if (certNo == null) {
            return false;
        }
        // 校验地区
        if (certNo.length() < 2 || !CERT_NO_AREA_MAPPING.containsKey(certNo.substring(0, 2))) {
            return false;
        }

        try {
            switch (certNo.length()) {
                case 15:
                    return validate15(certNo);
                case 18:
                    return validate18(certNo);
                default:
                    return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // 校验15位身份证号
    private static boolean validate15(String certNo) {
        int year = 1900 + Integer.parseInt(certNo.substring(6, 8));
        Pattern pattern;
        if (isLeapYear(year)) {
            pattern = LEAP_YEAR_15_PATTERN;
        } else {
            pattern = NORMAL_YEAR_15_PATTERN;
        }
        return pattern.matcher(certNo).matches();
    }

    // 校验18位身份证号
    private static boolean validate18(String certNo) {
        int year = Integer.parseInt(certNo.substring(6, 10));
        Pattern pattern;
        if (isLeapYear(year)) {
            pattern = LEAP_YEAR_18_PATTERN;
        } else {
            pattern = NORMAL_YEAR_18_PATTERN;
        }
        if (!pattern.matcher(certNo).matches()) {
            return false;
        }

        // 校验校验位
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += (certNo.charAt(i) - 48) * VERIFY_FACTOR[i];
        }
        int index = sum % 11;
        return VERIFY_CODES.charAt(index) == certNo.charAt(17);
    }

    // 是否是闰年
    private static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
    }
}
