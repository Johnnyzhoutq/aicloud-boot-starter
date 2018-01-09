/* 
 * Copyright © 2017 aicloud
 */

/*
 * 修订记录:
 * @author aicloud 2017-08-04 13:42 创建
 */
package com.aicloud.common.util.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * common-util自动配置类
 */
@Configuration
@Import({ConverterRegistryConfiguration.class, JpaScanConfiguration.class})
public class CommonUtilAutoConfiguration {
    // 本配置类的作用就是在spring-boot项目中自动导入相关配置类
}
