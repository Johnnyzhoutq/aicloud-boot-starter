/*
 * 修订记录:
 * @author 钟勋 2017-11-16 14:42 创建
 */
package com.aicloud.common.util.other;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 属性工具类
 */
public class PropertyUtils {

    /**
     * 获取属性
     *
     * @param key      属性key
     * @param defValue 默认值
     * @return 属性值
     */
    public static String getProperty(String key, String defValue) {
        String value = getProperty(key);
        if (value == null) {
            value = defValue;
        }
        return value;
    }

    /**
     * 获取属性
     *
     * @param key 属性key
     * @return 属性值
     */
    public static String getProperty(String key) {
        String value = System.getProperty(key);
        if (value == null) {
            for (String envKey : toEnvKeys(key)) {
                value = System.getenv(envKey);
                if (value != null) {
                    break;
                }
            }
        }
        return value;
    }

    /**
     * 将属性key转换为env环境中的key
     *
     * @param key 属性key
     * @return env环境中的key
     */
    public static String[] toEnvKeys(String key) {
        Set<String> keys = new LinkedHashSet<>();
        keys.addAll(Arrays.asList(toNoCaseChangeEnvKeys(key)));
        keys.addAll(Arrays.asList(toNoCaseChangeEnvKeys(key.toUpperCase())));

        return keys.toArray(new String[keys.size()]);
    }

    /**
     * 将属性key转换为env环境中的key（大小写不变）
     *
     * @param key 属性key
     * @return env环境中的key
     */
    public static String[] toNoCaseChangeEnvKeys(String key) {
        String noDotKey = key.replace('.', '_');
        String noHyphenKey = key.replace('-', '_');
        String noDotNoHyphenKey = noDotKey.replace('-', '_');

        Set<String> keys = new LinkedHashSet<>();
        keys.add(key);
        keys.add(noDotKey);
        keys.add(noHyphenKey);
        keys.add(noDotNoHyphenKey);
        return keys.toArray(new String[keys.size()]);
    }
}
