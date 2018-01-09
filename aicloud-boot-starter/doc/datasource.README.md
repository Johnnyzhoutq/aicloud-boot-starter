##@auther 周天琪
## 数据源配置说明

### 1. 单数据源场景：

配置：
```
#数据库url
aicloud.datasource.url=jdbc:mysql://localhost:3306/mydemo-ARK
#数据库用户名
aicloud.datasource.username=root
#数据库密码
aicloud.datasource.password=root
```

### 2. 多数据源场景：

配置：
```
#主数据源
#数据源名称
aicloud.datasource.datasource-name=myDatasource
aicloud.datasource.url=jdbc:mysql://localhost:3306/mydemo-ARK
aicloud.datasource.username=root
aicloud.datasource.password=root

#从数据源0
aicloud.datasources[0].datasourceName=myDatasource0
aicloud.datasources[0].url=jdbc:mysql://localhost:3306/mydemo-ARK0
aicloud.datasources[0].username=root
aicloud.datasources[0].password=root

#从数据源1
aicloud.datasources[1].datasourceName=myDatasource1
aicloud.datasources[1].url=jdbc:mysql://localhost:3306/mydemo-ARK1
aicloud.datasources[1].username=root
aicloud.datasources[1].password=root

#还可以继续配置...
```
主数据源会作为默认的数据源，执行过程中如果需要使用从数据源可以采用以下方式：

1、使用DataSourceHolder选择数据源

```
    // 选择从数据源1（入参是数据源名称）
    DataSourceHolder.chooseDataSource("myDatasource1");
    // 执行具体业务
    // 将数据源恢复成主数据源
    DataSourceHolder.chooseDefaultDataSource();
```

2、使用@DataSource注解选择数据源（注解标注的方法执行完后，数据源会恢复成主数据源）

```
    @DataSource("myDatasource1")
    public AddUserResult addUser(AddUserOrder order) {
        // 具体业务代码
    }
```

##### 读写分离是数据源为两个这种情况，根据根据以上多数据源介绍即可解决。

### 3. 查看sql执行情况

aicloud-boot内部数据源实现采用的是阿里巴巴开源的druid，如果需要查看sql执行情况可以直接在浏览器输入：
```
#url模板
http://ip:端口/druid/index.html
#例如我在本地开发时输入
http://localhost:8080/druid/index.html
```
