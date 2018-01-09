/* 
 * Copyright © 2017 aicloud
 */

/*
 * 修订记录:
 * @author aicloud 2017-08-04 13:42 创建
 */
package com.aicloud.common.util.tostring.format;

import com.aicloud.common.util.tostring.FieldFormatter;

import java.lang.reflect.Field;

/**
 * 隐藏属性的格式器（不输出属性）
 */
public class HideFieldFormatter implements FieldFormatter {
    @Override
    public void initialize(Field field) {
    }

    @Override
    public String format(Object obj) {
        return null;
    }
}
