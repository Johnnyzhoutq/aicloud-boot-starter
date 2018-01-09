/* 
 * Copyright © 2017 aicloud
 */

/*
 * 修订记录:
 * @author aicloud 2017-08-04 13:42 创建
 */
package com.aicloud.common.util.facade;

import javax.validation.constraints.Min;

/**
 * 抽象分页查询order（所有分页查询order的父类）
 */
public abstract class AbstractQueryOrder extends AbstractOrder {
    // 页码（从1开始）
    @Min(1)
    private int pageNo;
    // 每页大小
    @Min(1)
    private int pageSize;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
