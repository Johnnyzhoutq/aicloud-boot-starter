/* 
 * Copyright © 2017 aicloud
 */

/*
 * 修订记录:
 * @author aicloud 2017-08-04 13:42 创建
 */
package com.aicloud.common.util.tostring.format;

import com.aicloud.common.util.tostring.FieldFormat;

import java.lang.annotation.*;

/**
 * ToString时隐藏被标注的字段
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@FieldFormat(formattedBy = HideFieldFormatter.class)
public @interface Hide {
}
