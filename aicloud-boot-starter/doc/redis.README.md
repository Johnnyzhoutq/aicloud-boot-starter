##@auther 周天琪
## redis使用说明

### 1. 引入redis依赖：
```
    <dependency>
        <groupId>com.aicloud</groupId>
        <artifactId>aicloud-boot-starter-redis</artifactId>
        <version>${project.parent.version}</version>
    </dependency>
```
### 2. 组建介绍redis

1、默认序列化组件：
```
    提供了默认的序列化组件AicloudRedisObjectSerializer，已经集成进配置，用户不在需要关注该配置
```
2、默认redis配置：
```
   /**
	*Redis数据库索引（默认为0）
	**/
	private int database=0;
	/**
	*连接池最大连接数（使用负值表示没有限制）
	**/
	private int poolmaxactive = 30;
	/**
	*连接池最大阻塞等待时间（使用负值表示没有限制）毫秒
	**/
	private int poolmaxwait = 10000;
	/**
	*连接池中的最大空闲连接
	**/
	private int poolmaxidle = 5;
	/**
	*连接池中的最小空闲连接
	**/
	private int poolminidle = 5;
	/**
	*超时
	**/
	private int timeout = 5000;
	
	所以呢，理论上大家只需要配置host  port  password即可
	如果没有密码也可以不配置
```
3、yml配置
aicloud:
    redis:
        host: 10.200.2.104
        port: 6379
4、redis集群支持
aicloud:
    redis:
        cluster:
            nodes:
                - 192.168.80.145:7000
                - 192.168.80.145:7001
                - 192.168.80.145:7002
                - 192.168.80.146:7003
                - 192.168.80.146:7004
                - 192.168.80.146:7005