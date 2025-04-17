package com.oneluffy.consumer.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class CustomLoadBalancerConfiguration {

// Define a Spring Bean for a Reactor-based Load Balancer for Service Instances
    @Bean
    public ReactorLoadBalancer<ServiceInstance> reactorServiceInstanceLoadBalancer(
        // Inject the application's environment to access properties
            Environment environment,
        // Inject the LoadBalancerClientFactory to create load balancer instances
            LoadBalancerClientFactory loadBalancerClientFactory) {
    // Retrieve the property value associated with the LoadBalancerClientFactory's property name
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
    // Create and return a new RandomLoadBalancer instance
    // - The first argument is a lazy provider for ServiceInstanceListSupplier, which supplies the list of service instances
    // - The second argument is the name of the load balancer configuration
        return new RandomLoadBalancer(loadBalancerClientFactory
                .getLazyProvider(name, ServiceInstanceListSupplier.class),
                name);
    }
} 