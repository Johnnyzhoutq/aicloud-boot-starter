package com.aicloud.boot.mybatis.core;

import java.util.List;
import java.util.Map;

/**
 * 定制版MyBatis Mapper插件接口，如需其他接口参考官方文档自行添加。
 */
public interface BaseMapper<T>{
	int save(T model);//持久化
    int saveList(List<T> models);//批量持久化
    int deleteByMap(Map<String,String> paramMap);//通过Map刪除
    int deleteById(String id);//刪除
    int update(T model);//更新
    T findById(String id);//通过ID查找
    T findByObject(T model); //通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
    List<T> findByIds(String ids);//通过多个ID查找//eg：ids -> “1,2,3,4”
    List<T> findListByMap(Map<String,Object> paramMap);//根据条件查找
    List<T> findAll();//获取所有
}
