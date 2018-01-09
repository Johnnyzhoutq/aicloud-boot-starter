
/*
 * 修订记录:
 * @author 周天琪 2017-11-21 19:39 创建
 */
package com.aicloud.boot.jdbc.boot;

import com.aicloud.boot.jdbc.DataSourceHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源
 */
public class MultiDataSource extends AbstractRoutingDataSource {

    public MultiDataSource() {
        setLenientFallback(false);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getCurrentDataSource();
    }
}
