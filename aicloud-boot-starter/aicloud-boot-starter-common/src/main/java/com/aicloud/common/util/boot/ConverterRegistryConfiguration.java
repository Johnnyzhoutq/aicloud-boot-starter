/** 
 * @Copyright (c) 2018, 周天琪 johnny_ztq@163.com.com All Rights Reserved.
 * @PackageName:com.aicloud 
 * @Date:2018年2月8日上午9:39:02  
 * 
*/

package com.aicloud.common.util.boot;

import com.aicloud.common.util.money.MoneyConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 转换器注册配置类
 */
@Configuration
public class ConverterRegistryConfiguration {
    // 本工具所有的converter
    private static final List<Converter> CONVERTERS;

    static {
        CONVERTERS = new ArrayList<>();
        CONVERTERS.addAll(MoneyConverters.getConverters());
    }

    @Autowired(required = false)
    private List<ConverterRegistry> converterRegistries;

    // 初始化（将本工具内的所有converter注册到所有converter注册器中）
    @PostConstruct
    public void init() {
        if (converterRegistries == null) {
            return;
        }
        for (ConverterRegistry registry : converterRegistries) {
            for (Converter converter : CONVERTERS) {
                registry.addConverter(converter);
            }
        }
    }
}
