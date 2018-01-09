package com.aicloud.boot.mybatis.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * aicloudmybatis配置属性
 */
@ConfigurationProperties("aicloud.mybatis")
public class AicloudMybatisProperties {
    /**
     * 默认最大查询集合数
     */
    private int upperLimit = 10000;

	public int getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

}
