/* 
 * Copyright © 2017 aicloud
 */

/*
 * 修订记录:
 * @author aicloud 2017-08-04 13:42 创建
 */
package com.aicloud.common.util.tostring.format;

import com.aicloud.common.util.tostring.FieldFormatter;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/**
 * 隐藏数组、集合、Map类型属性细节格式器
 */
public class HideDetailFieldFormatter implements FieldFormatter {
    // 需隐藏细节的属性
    private Field field;
    // 被格式化的属性前段（属性名=）
    private String formattedPre;
    // 属性值格式器
    private ValueFormatter valueFormatter;

    @Override
    public void initialize(Field field) {
        this.field = field;
        formattedPre = field.getName() + "=";
        Class fieldType = field.getType();
        if (fieldType.isArray()) {
            valueFormatter = ValueFormatter.ARRAY;
        } else if (Collection.class.isAssignableFrom(fieldType)) {
            valueFormatter = ValueFormatter.COLLECTION;
        } else if (Map.class.isAssignableFrom(fieldType)) {
            valueFormatter = ValueFormatter.MAP;
        } else {
            throw new IllegalArgumentException("@HideDetail只能标注在数组、集合、Map类型上，" + field + "不属于这三种类型");
        }
    }

    @Override
    public String format(Object obj) {
        String formattedVal;
        Object fieldVal = ReflectionUtils.getField(field, obj);
        if (fieldVal == null) {
            formattedVal = null;
        } else {
            formattedVal = valueFormatter.format(fieldVal);
        }

        return formattedPre + formattedVal;
    }

    // 值格式器
    private enum ValueFormatter {
        // 数组格式器
        ARRAY {
            @Override
            public String format(Object obj) {
                return "[..." + Array.getLength(obj) + "...]";
            }
        },
        // 集合格式器
        COLLECTION {
            @Override
            public String format(Object obj) {
                return "[..." + ((Collection) obj).size() + "...]";
            }
        },
        // Map格式器
        MAP {
            @Override
            public String format(Object obj) {
                return "{..." + ((Map) obj).size() + "...}";
            }
        };

        /**
         * 格式化
         *
         * @param obj 待格式化的值
         * @return 值格式化后的字符串
         */
        public abstract String format(Object obj);
    }
}
