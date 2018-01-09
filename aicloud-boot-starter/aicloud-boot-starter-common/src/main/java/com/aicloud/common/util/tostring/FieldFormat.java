/* 
 * Copyright © 2017 aicloud
 */

/*
 * 修订记录:
 * @author aicloud 2017-08-04 13:42 创建
 */
package com.aicloud.common.util.tostring;

import java.lang.annotation.*;

/**
 * 属性格式化
 */
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldFormat {

    /**
     * 指定格式化器
     */
    Class<? extends FieldFormatter> formattedBy();

}
