package com.oneluffy.consul.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "consul-server")
public interface ConsulServerClient {
    
    @GetMapping("/consul/server")
    String test();
} 