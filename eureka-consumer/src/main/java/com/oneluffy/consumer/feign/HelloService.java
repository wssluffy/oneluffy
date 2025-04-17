package com.oneluffy.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "eureka-provider")
public interface HelloService {

    @GetMapping("/hello")
    String hello(@RequestParam(value = "name", defaultValue = "world") String name);
} 