package com.aicloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aicloud.service.OperationService;

@RestController
public class ComputeController {

    @Autowired
    private OperationService operationService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String add() {
    	String result = operationService.getMethodCall();
    	System.out.println(result);
        return result;
    }

}