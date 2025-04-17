package com.oneluffy.consumer.controller;

import com.oneluffy.consumer.feign.ProviderService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ConsumerController {

    @Autowired
    private ProviderService providerService;
    
    @Autowired
    private DiscoveryClient discoveryClient;
    
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    
    @Autowired
    private LoadBalancerClientFactory loadBalancerClientFactory;

    @GetMapping("/consumer/hello")
    public String hello(@RequestParam(defaultValue = "world") String name) {
        return "Consumer调用结果: " + providerService.hello(name);
    }
    
    /**
     * 演示Feign调用负载均衡
     */
    @GetMapping("/consumer/feign-lb")
    public Map<String, Object> feignLoadBalance() {
        Map<String, Object> result = new HashMap<>();
        // 连续调用10次provider服务，观察负载均衡效果
        List<Map<String, Object>> callResults = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            Map<String, Object> instanceInfo = providerService.instanceInfo();
            callResults.add(instanceInfo);
        }
        
        result.put("type", "Feign调用负载均衡");
        result.put("results", callResults);
        return result;
    }
    
    /**
     * 获取所有provider实例
     */
    @GetMapping("/consumer/instances")
    public List<ServiceInstance> instances() {
        return discoveryClient.getInstances("eureka-provider");
    }
    
    /**
     * 演示LoadBalancerClient负载均衡
     */
    @GetMapping("/consumer/ribbon-lb")
    public Map<String, Object> ribbonLoadBalance() {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> callResults = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            // 使用LoadBalancerClient选择服务实例
            ServiceInstance instance = loadBalancerClient.choose("eureka-provider");
            Map<String, Object> callInfo = new HashMap<>();
            callInfo.put("serviceId", instance.getServiceId());
            callInfo.put("host", instance.getHost());
            callInfo.put("port", instance.getPort());
            callInfo.put("uri", instance.getUri());
            callInfo.put("timestamp", System.currentTimeMillis());
            
            callResults.add(callInfo);
        }
        
        result.put("type", "LoadBalancer负载均衡");
        result.put("results", callResults);
        return result;
    }
    
    /**
     * 获取当前服务的负载均衡策略信息
     */
    @GetMapping("/consumer/lb-config")
    public Map<String, Object> loadBalancerConfig() {
        Map<String, Object> result = new HashMap<>();
        
        String serviceId = "eureka-provider";
        // 获取LoadBalancer实例
        ObjectProvider<ServiceInstanceListSupplier> provider = loadBalancerClientFactory.getLazyProvider(serviceId, ServiceInstanceListSupplier.class);
        ReactorLoadBalancer<ServiceInstance> loadBalancer = new RoundRobinLoadBalancer(provider, serviceId);
        
        result.put("serviceName", serviceId);
        result.put("loadBalancerClass", loadBalancer.getClass().getSimpleName());
        result.put("loadBalancerType", "Spring Cloud LoadBalancer");
        result.put("algorithm", "Round Robin (轮询)");
        
        return result;
    }
} 