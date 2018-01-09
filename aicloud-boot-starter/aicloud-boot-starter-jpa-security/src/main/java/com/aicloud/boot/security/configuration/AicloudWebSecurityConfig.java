package com.aicloud.boot.security.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableConfigurationProperties(AicloudSecuritySettings.class)
@EnableWebSecurity
public class AicloudWebSecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger logger = LogManager.getLogger(AicloudAccessDecisionManager.class);
	@Autowired
    private AicloudSecuritySettings settings;
	/**
	 * AuthenticationManager（接口）是认证相关的核心接口，也是发起认证的出发点，因为在实际需求中，我们可能会允许用户使用用户名+密码登录，同时允许用户使用邮箱+密码，手机号码+密码登录，甚至，可能允许用户使用指纹登录（还有这样的操作？没想到吧），所以说AuthenticationManager一般不直接认证，AuthenticationManager接口的常用实现类ProviderManager 内部会维护一个List<AuthenticationProvider>列表，存放多种认证方式，实际上这是委托者模式的应用（Delegate）。也就是说，核心的认证入口始终只有一个：AuthenticationManager，不同的认证方式：用户名+密码（UsernamePasswordAuthenticationToken），邮箱+密码，手机号码+密码登录则对应了三个AuthenticationProvider。这样一来四不四就好理解多了？熟悉shiro的朋友可以把AuthenticationProvider理解成Realm。在默认策略下，只需要通过一个AuthenticationProvider的认证，即可被认为是登录成功。
	*/
	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
    private AicloudCustomUserDetailsService aicloudCustomUserDetailsService;
	@Autowired
	AicloudAuthenticationSuccessHandler customSuccessHandler;
	/**
	 * 自定义登录接口获取登录信息
	*/
    @Bean
    UserDetailsService customUserService(){ //注册UserDetailsService 的bean
        System.out.println("使用自定义登录接口userService"+aicloudCustomUserDetailsService.toString());
    	return aicloudCustomUserDetailsService;
    }
    /**
	 * 用自定义登录接口生成验证管理器的builder
	*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("使用自定义登录接口aicloudCustomUserDetailsService 配置 AuthenticationManagerBuilder");
    	auth.userDetailsService(aicloudCustomUserDetailsService); 
    	//用aicloudCustomUserDetailsService 配置成默认的验证模式
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
	        .authorizeRequests()
		    	.antMatchers("/").permitAll()//请求路径"/"允许访问
		    	.anyRequest().authenticated() //其它请求都需要校验才能访问
		    	.and()
		    .formLogin()
		        .loginPage("/login").successHandler(customSuccessHandler)//定义登录的页面"/login"，允许访问，同时定义登陆成功后的处理对象
		        .permitAll()
		        .and()
		    .logout().logoutUrl("/logout").permitAll()
		    	.logoutSuccessUrl(settings.getLogoutsuccssurl())//退出登录后跳转的地址
		    .and()
                .exceptionHandling().accessDeniedPage(settings.getDeniedpage());//异常应该跳转的页面
        logger.info("http规则配置");
        System.out.println("http规则配置");

    }
    @Bean
    public AicloudFilterSecurityInterceptor customFilter() throws Exception{
        AicloudFilterSecurityInterceptor customFilter = new AicloudFilterSecurityInterceptor();
        customFilter.setSecurityMetadataSource(securityMetadataSource());
        customFilter.setAccessDecisionManager(accessDecisionManager());
        customFilter.setAuthenticationManager(authenticationManager);
        logger.info("自定义过滤器 CustomFilterSecurityInterceptor");
        System.out.println("自定义过滤器 CustomFilterSecurityInterceptor");
        return customFilter;
    }

    @Bean
    public AicloudAccessDecisionManager accessDecisionManager() {
    	logger.info("CustomAccessDecisionManager ");
        return new AicloudAccessDecisionManager();
    }

    @Bean
    public AicloudSecurityMetadataSource securityMetadataSource() {
    	logger.info("设置角色的访问权限 " + settings.getUrlroles());
    	System.out.println("设置角色的访问权限 " + settings.getUrlroles());
        return new AicloudSecurityMetadataSource(settings.getUrlroles());
        
    }
}

