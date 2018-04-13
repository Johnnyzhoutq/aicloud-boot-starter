##@auther 周天琪
## rabbitmq使用说明

### 1. 引入rabbitmq依赖：

```
    <dependency>
        <groupId>com.aicloud</groupId>
        <artifactId>aicloud-boot-starter-rabbitmq</artifactId>
        <version>${project.parent.version}</version>
    </dependency>
```
### 2. 组建介绍rabbitmq

1、默认序列化组件：
```
	对RabbitMessagingTemplate提供了复杂对象（自定义对象、list等）的支持（MappingJackson2MessageConverter）
	当然，你也可以对RabbitTemplate做同样的配置使其兼容复杂对象
```
2、rabbitmq配置：
```
	# RABBIT (RabbitProperties)
	spring.rabbitmq.addresses= # Comma-separated list of addresses to which the client should connect.
	spring.rabbitmq.cache.channel.checkout-timeout= # Number of milliseconds to wait to obtain a channel if the cache size has been reached.
	spring.rabbitmq.cache.channel.size= # Number of channels to retain in the cache.
	spring.rabbitmq.cache.connection.mode=channel # Connection factory cache mode.
	spring.rabbitmq.cache.connection.size= # Number of connections to cache.
	spring.rabbitmq.connection-timeout= # Connection timeout, in milliseconds; zero for infinite.
	spring.rabbitmq.dynamic=true # Create an AmqpAdmin bean.
	spring.rabbitmq.host=localhost # RabbitMQ host.
	spring.rabbitmq.listener.acknowledge-mode= # Acknowledge mode of container.
	spring.rabbitmq.listener.auto-startup=true # Start the container automatically on startup.
	spring.rabbitmq.listener.concurrency= # Minimum number of consumers.
	spring.rabbitmq.listener.default-requeue-rejected= # Whether or not to requeue delivery failures; default `true`.
	spring.rabbitmq.listener.idle-event-interval= # How often idle container events should be published in milliseconds.
	spring.rabbitmq.listener.max-concurrency= # Maximum number of consumers.
	spring.rabbitmq.listener.prefetch= # Number of messages to be handled in a single request. It should be greater than or equal to the transaction size (if used).
	spring.rabbitmq.listener.retry.enabled=false # Whether or not publishing retries are enabled.
	spring.rabbitmq.listener.retry.initial-interval=1000 # Interval between the first and second attempt to deliver a message.
	spring.rabbitmq.listener.retry.max-attempts=3 # Maximum number of attempts to deliver a message.
	spring.rabbitmq.listener.retry.max-interval=10000 # Maximum interval between attempts.
	spring.rabbitmq.listener.retry.multiplier=1.0 # A multiplier to apply to the previous delivery retry interval.
	spring.rabbitmq.listener.retry.stateless=true # Whether or not retry is stateless or stateful.
	spring.rabbitmq.listener.transaction-size= # Number of messages to be processed in a transaction. For best results it should be less than or equal to the prefetch count.
	spring.rabbitmq.password= # Login to authenticate against the broker.
	spring.rabbitmq.port=5672 # RabbitMQ port.
	spring.rabbitmq.publisher-confirms=false # Enable publisher confirms.
	spring.rabbitmq.publisher-returns=false # Enable publisher returns.
	spring.rabbitmq.requested-heartbeat= # Requested heartbeat timeout, in seconds; zero for none.
	spring.rabbitmq.ssl.enabled=false # Enable SSL support.
	spring.rabbitmq.ssl.key-store= # Path to the key store that holds the SSL certificate.
	spring.rabbitmq.ssl.key-store-password= # Password used to access the key store.
	spring.rabbitmq.ssl.trust-store= # Trust store that holds SSL certificates.
	spring.rabbitmq.ssl.trust-store-password= # Password used to access the trust store.
	spring.rabbitmq.ssl.algorithm= # SSL algorithm to use. By default configure by the rabbit client library.
	spring.rabbitmq.template.mandatory=false # Enable mandatory messages.
	spring.rabbitmq.template.receive-timeout=0 # Timeout for `receive()` methods.
	spring.rabbitmq.template.reply-timeout=5000 # Timeout for `sendAndReceive()` methods.
	spring.rabbitmq.template.retry.enabled=false # Set to true to enable retries in the `RabbitTemplate`.
	spring.rabbitmq.template.retry.initial-interval=1000 # Interval between the first and second attempt to publish a message.
	spring.rabbitmq.template.retry.max-attempts=3 # Maximum number of attempts to publish a message.
	spring.rabbitmq.template.retry.max-interval=10000 # Maximum number of attempts to publish a message.
	spring.rabbitmq.template.retry.multiplier=1.0 # A multiplier to apply to the previous publishing retry interval.
	spring.rabbitmq.username= # Login user to authenticate to the broker.
	spring.rabbitmq.virtual-host= # Virtual host to use when connecting to the broker.
	https://my.oschina.net/u/2353881/blog/875389
	
	
	上述为可选配置，开启ack等
	如果开启ack ，必须实现RabbitTemplate.ConfirmCallback接口，定义回调方法  类似于：
	rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容  
	
	
	/**  
     * 回调  
     */  
    @Override  
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {  
        System.out.println(" 回调id:" + correlationData);  
        if (ack) {  
            System.out.println("消息成功消费");  
        } else {  
            System.out.println("消息消费失败:" + cause);  
        }  
    }  
```
3、yml配置
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: zhoutianqi
    password: zhoutianqi
    
    
    
    
#或者  RabbitMQ单机，只使用addresses
#spring:
 # rabbitmq:
   # addresses:ip1:port1
  #  username: your_username
   # password: your_password


#//RabbitMQ集群，addresses一定要逗号分隔
#spring:
  #rabbitmq:
   # addresses:ip1:port1,ip2:port2,ip3:port3
   # username: your_username
    #password: your_password