package com.aicloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@SpringCloudApplication
public class Application {
	/**   
	* @Title: Application.java  
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author ZhouTianQi   
	* @date 2017年1月2日 下午3:49:01 
	* @version V1.0   
	*/
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	/*@Bean
    public IRule ribbonRule() {
        return new RandomRule();//这里配置策略，和配置文件对应
    }*/
	
	/**
	 * Spring Cloud还可以通过使用@RibbonClient声明其他配置
	 * 自定义Ribbon的负载均衡
	 * @RibbonClient(name = "springcloud-userservcie", configuration = RibbonConfig.class)
	 * @RibbonClient，对name为(springcloud-userservcie)的服务端做RibbonConfig.java类中自定义的负载均衡策略
	 * 
	 * 如果使用@feignClient做负载均衡    可以更像是本地调用一样处理远程服务
	 * 在application.yml中指定对应服务的负载均衡策略
	 * service-B.ribbon.NFLoadBalancerRuleClassName=com.netflix.loadbalancer.RandomRule
	 * 
	*/
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
