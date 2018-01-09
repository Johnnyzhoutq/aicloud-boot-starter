##@auther 周天琪
## kafka使用说明

### 1. 引入kafka依赖：
```
    <dependency>
        <groupId>com.aicloud</groupId>
        <artifactId>aicloud-boot-starter-kafka</artifactId>
        <version>${project.parent.version}</version>
    </dependency>
```
### 2. 组建介绍kafka

1、默认序列化组件：
```
	默认使用官方提供的基于String的系列化、反序列化组件，当然呢，也推荐大家这么使用。因为你不可能针对每个实例都创建一个
	序列化，使用过程中总是会有各种信息结构，建议统一下
```
2、默认配置：
```
	参照https://my.oschina.net/u/2353881/blog/875389
```
3、使用方式
```
	见demo
```
