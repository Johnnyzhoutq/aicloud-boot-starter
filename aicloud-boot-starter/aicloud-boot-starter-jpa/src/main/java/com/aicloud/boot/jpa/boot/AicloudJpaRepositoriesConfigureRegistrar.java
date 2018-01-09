/* 
 * Copyright © 2017 www.aicloud.kim
 */

/*
 * 修订记录:
 * @author 周天琪 2017-09-04 11:20 创建
 */
package com.aicloud.boot.jpa.boot;

import com.aicloud.boot.jpa.support.JpaQueryRepository;
import org.springframework.boot.autoconfigure.data.AbstractRepositoryConfigurationSourceSupport;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;

import java.lang.annotation.Annotation;

/**
 * jpa-repository配置类（设置repository实现基础类）
 * <p>
 * 参考{@link org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfigureRegistrar}
 */
public class AicloudJpaRepositoriesConfigureRegistrar extends AbstractRepositoryConfigurationSourceSupport {

    @Override
    protected Class<? extends Annotation> getAnnotation() {
        return EnableJpaRepositories.class;
    }

    @Override
    protected Class<?> getConfiguration() {
        return EnableLmmJpaRepositoriesConfiguration.class;
    }

    @Override
    protected RepositoryConfigurationExtension getRepositoryConfigurationExtension() {
        return new JpaRepositoryConfigExtension();
    }

    @EnableJpaRepositories(repositoryBaseClass = JpaQueryRepository.class)
    private static class EnableLmmJpaRepositoriesConfiguration {
    }
}
