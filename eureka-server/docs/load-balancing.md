# Spring Cloud LoadBalancer 负载均衡说明

## 负载均衡原理

Spring Cloud LoadBalancer是Spring官方提供的客户端负载均衡器，用于替代Netflix Ribbon。它的工作原理如下：

1. **服务发现**
   - 服务提供者启动时向Eureka Server注册
   - 服务消费者从Eureka Server获取服务列表
   - 定期更新服务列表保持最新状态

2. **负载均衡过程**
   - 消费者发起请求时，LoadBalancer拦截请求
   - 从服务列表中按策略选择一个实例
   - 将请求转发到选中的实例
   - 处理响应并返回结果

3. **故障处理**
   - 自动剔除不健康的实例
   - 支持重试机制
   - 服务恢复后自动加回

## 负载均衡策略

Spring Cloud LoadBalancer提供以下负载均衡策略：

1. **轮询（Round Robin）- 默认策略**
   - 按顺序依次选择服务实例
   - 适用于服务器性能相近的情况
   ```java
   new RoundRobinLoadBalancer(supplier, "eureka-provider");
   ```

2. **随机（Random）**
   - 随机选择一个服务实例
   - 适用于服务器性能差异不大的情况
   ```java
   new RandomLoadBalancer(supplier, "eureka-provider");
   ```

3. **自定义策略**
   - 实现ReactorServiceInstanceLoadBalancer接口
   - 根据业务需求自定义选择逻辑

## 配置方式

### 1. 配置类方式

```java
@Configuration
public class CustomLoadBalancerConfiguration {
    @Bean
    public ReactorLoadBalancer<ServiceInstance> reactorServiceInstanceLoadBalancer(
            Environment environment,
            LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new RandomLoadBalancer(loadBalancerClientFactory
                .getLazyProvider(name, ServiceInstanceListSupplier.class),
                name);
    }
}
```

使用配置：
```java
@LoadBalancerClient(name = "eureka-provider", 
                   configuration = CustomLoadBalancerConfiguration.class)
```

### 2. 配置文件方式

在application.yml中配置：

```yaml
spring:
  cloud:
    loadbalancer:
      <service-id>:
        enabled: true
        retry:
          enabled: true
        health-check:
          path: /actuator/health
          interval: 30s
```

## 重试机制

Spring Cloud LoadBalancer支持请求重试：

```yaml
spring:
  cloud:
    loadbalancer:
      retry:
        enabled: true
        max-retries-on-same-service-instance: 1
        max-retries-on-next-service-instance: 2
        retry-on-all-operations: true
        backoff:
          enabled: true
          initial-interval: 1000
          max-interval: 5000
          multiplier: 2
```

## 健康检查

配置健康检查以剔除不健康的实例：

```yaml
spring:
  cloud:
    loadbalancer:
      health-check:
        enabled: true
        path: 
          default: /actuator/health
        interval: 30s
        initial-delay: 10s
```

## 监控指标

Spring Cloud LoadBalancer提供以下监控指标：

1. **实例选择**
   - 选择次数
   - 选择耗时
   - 选择失败次数

2. **请求统计**
   - 请求总数
   - 成功请求数
   - 失败请求数
   - 重试次数

可通过Actuator的/metrics端点查看：
```
/actuator/metrics/spring.cloud.loadbalancer.*
```

## 最佳实践

1. **合理配置重试**
   - 避免过多重试导致系统负载过高
   - 设置合适的超时时间

2. **启用健康检查**
   - 及时发现并剔除不健康实例
   - 设置合适的检查间隔

3. **选择合适的负载均衡策略**
   - 考虑服务器性能差异
   - 考虑业务特点

4. **监控和告警**
   - 监控负载均衡指标
   - 设置合理的告警阈值

## 常见问题

1. **服务发现延迟**
   - 原因：服务注册后需要等待服务列表刷新
   - 解决：适当调整刷新间隔

2. **重试导致超时**
   - 原因：重试次数过多或间隔过长
   - 解决：调整重试参数

3. **负载不均衡**
   - 原因：服务器性能差异大
   - 解决：考虑使用权重策略

4. **健康检查不准确**
   - 原因：检查间隔过长或路径配置错误
   - 解决：调整健康检查配置 