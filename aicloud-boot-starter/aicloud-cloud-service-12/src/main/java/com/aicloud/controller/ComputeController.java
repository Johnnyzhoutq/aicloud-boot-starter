package com.aicloud.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ComputeController {

    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;
    private String from = "字符串";
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/api/getMethodCall" ,method = RequestMethod.GET)
    public String getMethodCall() {
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/api/getMethodCall, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() +"aicloud-cloud-service-1:" + from);
        return "底层接口From aicloud-cloud-service-12, Result is " + from;
    }
}