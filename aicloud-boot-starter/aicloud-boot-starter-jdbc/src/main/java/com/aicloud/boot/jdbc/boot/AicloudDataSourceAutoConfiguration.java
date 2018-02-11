/** 
 * @Copyright (c) 2018, 周天琪 johnny_ztq@163.com.com All Rights Reserved.
 * @PackageName:com.aicloud 
 * @Date:2018年2月8日上午9:39:02  
 * 
*/

package com.aicloud.boot.jdbc.boot;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import com.alibaba.druid.pool.DruidDataSource;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * aicloud数据源配置
 */
@Configuration
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(AicloudDataSourceProperties.class)
public class AicloudDataSourceAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(AicloudDataSourceAutoConfiguration.class);

    // 数据源配置属性
    @Autowired
    private AicloudDataSourceProperties properties;
    // 创建的所有数据源
    private Set<DruidDataSource> dataSources = new HashSet<>();

    @Bean(destroyMethod = "")
    public DataSource dataSource() {
        properties.check();
        DataSource defaultDataSource = buildDruidDataSource(properties.getDatasource());
        if (CollectionUtils.isEmpty(properties.getDatasources())) {
            return defaultDataSource;
        }
        // 多数据源情况
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(properties.getDatasource().getDatasourceName(), defaultDataSource);
        for (AicloudDataSourceProperties.DetailProperties detailProperties : properties.getDatasources()) {
            targetDataSources.put(detailProperties.getDatasourceName(), buildDruidDataSource(detailProperties));
        }

        MultiDataSource dataSource = new MultiDataSource();
        dataSource.setDefaultTargetDataSource(defaultDataSource);
        dataSource.setTargetDataSources(targetDataSources);

        return dataSource;
    }

    @PreDestroy
    public void destroy() {
        for (DruidDataSource dataSource : dataSources) {
            try {
                dataSource.close();
            } catch (Throwable e) {
                logger.error("关闭druid数据源出错：", e);
            }
        }
    }

    // 构建druid数据源
    private DruidDataSource buildDruidDataSource(AicloudDataSourceProperties.DetailProperties detailProperties) {
        DatabaseDriver driver = DatabaseDriver.fromJdbcUrl(detailProperties.getUrl());

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver.getDriverClassName());
        dataSource.setUrl(detailProperties.getUrl());
        dataSource.setUsername(detailProperties.getUsername());
        dataSource.setPassword(detailProperties.getPassword());
        dataSource.setInitialSize(detailProperties.getInitialSize());
        dataSource.setMinIdle(detailProperties.getMinIdle());
        dataSource.setMaxActive(detailProperties.getMaxActive());
        dataSource.setValidationQuery(driver.getValidationQuery());
        try {
            dataSource.setFilters("stat,wall");
            dataSource.init();
        } catch (SQLException e) {
            ExceptionUtils.rethrow(e);
        }
        dataSources.add(dataSource);

        return dataSource;
    }
}
