
/*
 * 修订记录:
 * @author 周天琪 2017-11-27 14:22 创建
 */
package com.aicloud.boot.mybatis.boot;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import com.github.pagehelper.autoconfigure.PageHelperProperties;

/**
 * aicloudmybatis自动配置类
 */
@Configuration
@AutoConfigureBefore(PageHelperAutoConfiguration.class)
@EnableConfigurationProperties({AicloudMybatisProperties.class,PageHelperProperties.class})
public class AicloudMybatisAutoConfiguration {
    // 配置属性
	@Autowired
    private AicloudMybatisProperties properties;
    @Autowired
    private PageHelperProperties pageHelperProperties;

    @PostConstruct
    public void init() {
        // 定制PageHelper
        if (StringUtils.isEmpty(pageHelperProperties.getSupportMethodsArguments())) {
            pageHelperProperties.setSupportMethodsArguments("true");
        }
        if (StringUtils.isEmpty(pageHelperProperties.getReasonable())) {
            pageHelperProperties.setReasonable("true");
        }
        pageHelperProperties.setDialect("com.aicloud.boot.mybatis.AicloudPageHelper");
        pageHelperProperties.getProperties().setProperty("upperLimit", Integer.toString(properties.getUpperLimit()));
    }

    // 配置扫描mapper接口
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        configurer.setBasePackage("com.aicloud.mapper");
        return configurer;
    }
}
