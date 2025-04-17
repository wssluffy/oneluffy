package com.oneluffy.consumer.controller;

import com.oneluffy.consumer.feign.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/consumer/hello")
    public String hello(@RequestParam(defaultValue = "world") String name) {
        return "Consumer调用结果: " + helloService.hello(name);
    }
} 