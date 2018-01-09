
/*
 * 修订记录:
 * @author 周天琪 2017-11-22 10:36 创建
 */
package com.aicloud.boot.jdbc.boot;

import com.aicloud.boot.jdbc.DataSource;
import com.aicloud.boot.jdbc.DataSourceHolder;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 选择数据源注解aop
 */
@Component
@Aspect
public class DataSourceAop {

    @Before("@annotation(dataSource)")
    public void before(DataSource dataSource) {
        DataSourceHolder.chooseDataSource(dataSource.value());
    }

    @After("@annotation(dataSource)")
    public void after(DataSource dataSource) {
        DataSourceHolder.chooseDefaultDataSource();
    }
}
