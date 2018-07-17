# linux下的配置和使用
## 0、配置注册中心
用解压缩工具打开aicloud-cloud-nginxeureka-1.0-SNAPSHOT-exec.jar\BOOT-INF\classes\application.yml文件，这是一个标注的spring cloud配置
```
eureka:
  instance:
    prefer-ip-address: false
    instance-id: ${spring.cloud.client.ipAddress}:${server.port}
  client:
    serviceUrl:
      securityAuth: user:user 		#假设你集成了security需要权限验证
      httpUrl: http://peer1:9001/eureka/        #获取app信息的请求地址
      defaultZone: http://user:user@peer1:9001/eureka/
server:
  tomcat:
    uri-encoding: UTF-8
  port: 9999
  context-path: /ngineureka
spring:
  application:
    name: ngineureka
```
请将defaultZone改为注册中心地址

## 1、修改config.properties配置信息
一个配置的例子如下：

confPath指定一个文件夹，用于存放生成的nginx配置信息；

heartbeatCycle指定查询注册中心的周期(秒)；

recordCacheSize是可选的，记录最近操作的次数
```
confPath=/usr/local/nginx/conf/apps
heartbeatCycle=300
recordCacheSize=10
```

## 2、修改nginx.conf
在相应的位置添加 “include {confPath}/ngineureka_upstream.conf;” “include {confPath}/ngineureka_upstream.conf;”两行，引入ngineureka启动后生成的配置


切记：手动创建apps文件夹，否则会报错，因为代码逻辑上不会创建文件夹

一个配置的例子如下(仅演示http块):

```
http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  65;
    #gzip  on;
    
    include apps/ngineureka_upstream.conf;
    
    server {
        listen       7082;
        server_name  localhost;
		
	    proxy_buffer_size   128k;
	    proxy_buffers   4 256k;
	    proxy_busy_buffers_size   256k;
	    default_type 'text/html';
	    charset utf-8;
	    include apps/ngineureka_location.conf;
    }
}
```

## 3、修改reload.sh文件
reload.sh文件如下:
```
/usr/local/nginx/sbin/nginx -s reload
```
修改第一行，使得sh命令能够切换到您的nginx根目录
通过chmod命令，赋予reload.sh文件执行权限

## 可选操作1、为location块添加参数(自版本1.1.1)
有时候，我们需要对location块添加一下参数，以满足性能，安全性等需求，比如:
```
location ^~ /xx/ {
	proxy_pass http://upstream_xx;
	deny 192.168.1.2;
	deny 192.168.1.3;
}
```
此时，我们可以配置根目录下的文件locationParam.txt(没有则新建一个)
```
>xx
deny 192.168.1.2;
deny 192.168.1.3;

>xx1
proxy_set_header  X-Real-IP  $remote_addr;
```
">"开头的行表示应用名称，根据前面的约定，会被转为全小写
下面跟着的行就是该应用对应的location块下要加的参数了

## 4、启动
./startup.sh启动服务
您可以通过查看{confPath}/ngineureka_upstream.conf文件，检查配置信息是否正确地添加到nginx

然后，就能通过nginx，访问到您的rest服务了~