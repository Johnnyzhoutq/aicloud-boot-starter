/* 
 * Copyright © 2017 aicloud
 */

/*
 * 修订记录:
 * @author aicloud 2017-08-04 13:42 创建
 */
package com.aicloud.common.util.facade;

import com.aicloud.common.util.tostring.format.HideDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象分页result（所有分页result的父类）
 */
public abstract class AbstractQueryResult<T> extends AbstractResult {
    // 记录总数
    private long totalCount;
    // 查询出的当前页详细数据
    @HideDetail
    private List<T> infos = new ArrayList<>();

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getInfos() {
        return infos;
    }

    public void addInfo(T info) {
        infos.add(info);
    }
}
