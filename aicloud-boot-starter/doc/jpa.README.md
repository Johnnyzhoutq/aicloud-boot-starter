##@auther 周天琪
## jpa使用说明

### 1. 引入jpa依赖：

```
    <dependency>
        <groupId>com.aicloud</groupId>
        <artifactId>aicloud-boot-starter-jpa</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```
### 2. 使用jpa
原生的spring-boot集成的jpa已经非常好用，只是对于分页查询需要自己动态构建Specification，比较麻烦。因此lmm-boot对其进行了扩展，简化分页查询。扩展的jpa和原生的jpa是完全兼容的。

在此只介绍扩展的分页查询，对于原生的能力可以查看[spring-data-jpa官方文档](https://docs.spring.io/spring-data/jpa/docs/current/reference/html)。

1、定义dao：
```
    // 也可以使用接口形式：自己的dao接口继承com.aicloud.boot.jpa.QueryRepository
    @RepositoryDefinition(domainClass = User.class, idClass = Long.class)
    public interface UserDao {
        // query为lmm-boot扩展的分页查询接口
        Page<User> query(Map<String, Object> searchParams, Pageable pageable);
    }
```
2、构造查询条件：
```
    Map<String, Object> searchParams = new HashMap<>();
    if (queryUserOrder.getUserName() != null) {
        // 查询条件格式参照SpecificationUtils
        searchParams.put("LIKE_userName", "%" + queryUserOrder.getUserName() + "%");
    }
```
3、执行分页查询：
```
    @Autowired
    private UserDao userDao;
    //查询第1页，每页10条数据
    Page<User> page = userDao.query(searchParams, new PageRequest(0, 10));
```
4、yml配置
spring:
    jpa:
        hibernate.ddl-auto: update
        show-sql: true