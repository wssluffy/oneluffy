package com.oneluffy.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import com.oneluffy.consul.config.CustomLoadBalancerConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@LoadBalancerClient(name = "eureka-provider", configuration = CustomLoadBalancerConfiguration.class)
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
} 