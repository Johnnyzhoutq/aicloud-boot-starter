/** 
 * @Copyright (c) 2018, 周天琪 johnny_ztq@163.com.com All Rights Reserved.
 * @PackageName:com.singleBean 
 * @Date:2018年2月8日上午9:39:02  
 * 
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
