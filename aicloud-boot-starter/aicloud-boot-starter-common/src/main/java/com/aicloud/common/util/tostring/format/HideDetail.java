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
 * 数组、集合、Map类型属性ToString时隐藏细节
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@FieldFormat(formattedBy = HideDetailFieldFormatter.class)
public @interface HideDetail {
}
