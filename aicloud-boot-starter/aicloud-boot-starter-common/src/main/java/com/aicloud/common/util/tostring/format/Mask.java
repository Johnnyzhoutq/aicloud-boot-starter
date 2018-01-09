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
 * ToString时掩码
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@FieldFormat(formattedBy = MaskFieldFormatter.class)
public @interface Mask {

    /**
     * 是否全部掩码
     * （true：得到固定长度的掩码字符串；false：程序自动判断需掩码的部分）
     */
    boolean allMask() default false;

    /**
     * 掩码字符（默认'*'）
     */
    char maskChar() default '*';

}
