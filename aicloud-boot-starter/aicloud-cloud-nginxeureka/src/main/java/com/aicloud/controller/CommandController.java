package com.aicloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aicloud.service.NgineurekaService;

/**
 * 操作Controller
 *
 * @author liuyu
 * @date 2018/3/9
 */
@RestController()
@RequestMapping("/cmd")
public class CommandController {
    @Autowired
    private NgineurekaService ngineurekaService;

    @RequestMapping("reload")
    public String reload() {
        ngineurekaService.doOne(true);
        return "success";
    }
}
