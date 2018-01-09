/* 
 * Copyright © 2017 www.lvmama.com
 */

/*
 * 修订记录:
 * @author 周天琪 2017-08-24 11:14 创建
 */
package com.aicloud.boot.redis.boot;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * spring注解式环境实现设置
 */
public class AicloudRedisApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        String cacheType = event.getEnvironment().getProperty("spring.cache.type");
        if (StringUtils.isEmpty(cacheType)) {
            System.setProperty("spring.cache.type", "redis");
        }
    }
}
