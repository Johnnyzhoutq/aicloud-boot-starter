/** 
 * @Copyright (c) 2018, 周天琪 johnny_ztq@163.com.com All Rights Reserved.
 * @PackageName:com.aicloud 
 * @Date:2018年2月8日上午9:39:02  
 * 
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
