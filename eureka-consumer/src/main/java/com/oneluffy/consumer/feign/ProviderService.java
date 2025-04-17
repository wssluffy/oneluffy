package com.oneluffy.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "eureka-provider")
public interface ProviderService {

    @GetMapping("/hello")
    String hello(@RequestParam(value = "name", defaultValue = "world") String name);
    
    @GetMapping("/instance-info")
    Map<String, Object> instanceInfo();
} 