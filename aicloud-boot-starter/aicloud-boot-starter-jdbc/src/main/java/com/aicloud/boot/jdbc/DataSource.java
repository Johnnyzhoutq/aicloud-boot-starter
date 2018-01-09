
/*
 * 修订记录:
 * @author 周天琪 2017-11-22 10:32 创建
 */
package com.aicloud.boot.jdbc;

import java.lang.annotation.*;

/**
 * 选择数据源
 */
@Documented
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    /**
     * 数据源名称
     */
    String value();
}
