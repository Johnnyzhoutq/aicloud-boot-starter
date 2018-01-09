/* 
 * Copyright © 2017 www.aicloud.kim
 */

/*
 * 修订记录:
 * @author 周天琪 2017-09-04 11:20 创建
 */
package com.aicloud.boot.jpa.boot;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;

import javax.sql.DataSource;

/**
 * jpa-repository自动配置类（导入相关配置类）
 * <p>
 * 由于spring-boot原生的集成spring-data-jpa没有提供扩展点，需要扩展功能的话只能自己写引导，参考{@link JpaRepositoriesAutoConfiguration}
 */
@Configuration
@ConditionalOnBean(DataSource.class)
@ConditionalOnClass(JpaRepository.class)
@ConditionalOnMissingBean({JpaRepositoryFactoryBean.class, JpaRepositoryConfigExtension.class})
@AutoConfigureAfter(HibernateJpaAutoConfiguration.class)
@AutoConfigureBefore(JpaRepositoriesAutoConfiguration.class)
@Import(AicloudJpaRepositoriesConfigureRegistrar.class)
public class AicloudJpaRepositoriesAutoConfiguration {
}
