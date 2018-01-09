/* 
 * Copyright © 2017 www.aicloud.kim
 */

/*
 * 修订记录:
 * @author 周天琪 2017-09-04 11:20 创建
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
