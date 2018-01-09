/* 
 * Copyright © 2017 aicloud
 */

/*
 * 修订记录:
 * @author aicloud 2017-08-04 13:42 创建
 */
package com.aicloud.common.util.tostring;

import java.lang.reflect.Field;

/**
 * 属性格式化器（实现类必须提供默认构造函数）
 */
public interface FieldFormatter {

    /**
     * 初始化（一个格式化器实例只被执行一次）
     *
     * @param field 被标注的属性
     */
    void initialize(Field field);

    /**
     * 格式化（此方法会被并发调用，实现类需保证线程安全）
     *
     * @param obj 被ToString的对象
     * @return 属性格式化后的字符串（一般是name="abc"这种格式），返回null表示此属性被隐藏
     */
    String format(Object obj);

}
