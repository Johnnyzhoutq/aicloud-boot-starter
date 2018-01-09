##@auther 周天琪
## mybatis使用说明

### 1. 添加mybatis依赖：

<dependency>
    <groupId>com.aicloud</groupId>
    <artifactId>aicloud-boot-starter-mybatis</artifactId>
    <version>${project.parent.version}</version>
</dependency>
```
### 2. 配置：
以下配置均有默认配置
```
#mapper接口扫描路径（选填，默认是项目的boot模块下的mapper包路径下）
com.aicloud.mapper
#pojo实体类路径（必填）
mybatis.typeAliasesPackage=com.aicloud.entity
#xml文件路径
classpath:mapper/**/*Mapper.xml

```
### 3. 亮点：
亮点：
重申一遍,亮点哦：
A:提供了统一的mapper接口
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
B：提供了对统一的mapper接口的service层封装以及抽象化实现AbstractService

###4.使用mybatis：
1、在com.aicloud.mapper下创建mapper接口：

```
public interface UserMapper extends BaseMapper<User>{
    
}
```
2、在classpath:/mybatis/mapper目录下创建mapper的xml文件：

```
<!--在此只粘贴关键部分-->
<resultMap id="BasePojoMap" type="com.aicloud.entity.User">
        <id  property="userId" column="user_id"/>
		<result  property="name" column="name"/>
    </resultMap>
    <insert id="save" parameterType="com.aicloud.entity.User">
	    insert into t_user (user_id, name)
	    values (#{userId,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR})
  </insert>
  <select id="findAll" resultMap="BasePojoMap">
  		select * from t_user
  </select>
```
3、接口
public interface UserService extends BaseService<User>{
	
}
```
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
	
}
### 4. 分页查询
抽象化实现AbstractService中已经集成分页，大家根据需求传入page和size,不需要分页的话直接null,null
```
public List<T> findAll(Integer page,Integer pageSize) {
    	if(page!=null && pageSize!=null && page>-1 && pageSize>0){
    		AicloudPageHelper.startPage(page, pageSize);
    	}
        return mapper.findAll();
    }
```