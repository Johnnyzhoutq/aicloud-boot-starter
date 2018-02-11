/** 
 * @Copyright (c) 2018, 周天琪 johnny_ztq@163.com.com All Rights Reserved.
 * @PackageName:com.aicloud 
 * @Date:2018年2月8日上午9:39:02  
 * 
*/

package com.aicloud.boot.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 查询repository
 * （所有方法的searchParams参数的格式参照{@link SpecificationUtils#parse(Map)}）
 */
@NoRepositoryBean
public interface QueryRepository<T, ID extends Serializable> extends Repository<T, ID> , JpaRepository<T, ID>, JpaSpecificationExecutor<T>{

    /**
     * 分页查询
     *
     * @param searchParams 条件参数
     * @param pageable     分页信息
     */
    Page<T> query(Map<String, Object> searchParams, Pageable pageable);

    /**
     * 查询
     *
     * @param searchParams 条件参数
     */
    List<T> query(Map<String, Object> searchParams);

    /**
     * 排序查询
     *
     * @param searchParams 条件参数
     * @param sort         排序
     */
    List<T> query(Map<String, Object> searchParams, Sort sort);

    /**
     * 统计
     *
     * @param searchParams 条件参数
     */
    long count(Map<String, Object> searchParams);
}
