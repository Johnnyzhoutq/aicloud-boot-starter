
/*
 * 修订记录:
 * @author 周天琪 2017-11-28 15:47 创建
 */
package com.aicloud.boot.mybatis.boot;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * mapper的xml文件路径设置
 */
public class MapperLocationsApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    /**
     * 默认的mapper的xml文件路径
     */
    public static final String DEFAULT_MAPPER_LOCATION = "classpath:mapper/**/*Mapper.xml";
    // mapper的xml文件路径配置属性名
    private static final String MAPPER_LOCATIONS_PROPERTY_NAME = "mybatis.mapper-locations";

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
            System.setProperty(MAPPER_LOCATIONS_PROPERTY_NAME, DEFAULT_MAPPER_LOCATION);
    }
}
