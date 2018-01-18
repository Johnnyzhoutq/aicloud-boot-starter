package com.aicloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccessFilter extends ZuulFilter  {
	/**   
	* @Title: AccessFilter.java  
	* @Description: TODO(自定义网关过滤器) 
	* @author ZhouTianQi   
	* @date 2017年1月2日 下午3:49:01 
	* @version V1.0   
	*/

    private static Logger log = LogManager.getLogger(AccessFilter.class);

    /**
     * 请求转发前 前置过滤器  
    */
    @Override
    public String filterType() {
        return "pre";
    }
    /**
     * 优先级为0，数字越大，优先级越低
    */
    @Override
    public int filterOrder() {
        return 0;
    }
    /**
     * 是否执行该过滤器，此处为true，说明需要过滤 
    */
    @Override
    public boolean shouldFilter() {
        return true;
    }
    
    /**
     * 该过滤器只是简单验证请求必须携带accessToken参数而已
    */
    @Override
    public Object run() {
    	//实际上实现的是threadLocal 线程绑定的
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        Object accessToken = request.getParameter("accessToken");
        if(accessToken == null) {
            log.warn("access token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        log.info("access token ok");
        return null;
    }

}
