# Eureka 负载均衡机制

Spring Cloud Eureka 与 Ribbon 集成，提供基于客户端的负载均衡能力。本文档介绍负载均衡的原理和测试方法。

## 负载均衡原理

1. **服务注册**：多个相同服务实例(Provider)注册到Eureka Server，每个实例有不同的实例ID
2. **服务发现**：Consumer从Eureka Server获取Provider的所有实例列表
3. **负载均衡策略**：使用Ribbon进行负载均衡，默认采用轮询算法

## 负载均衡实现方式

Spring Cloud中的负载均衡主要有两种实现方式：

### 1. Ribbon 负载均衡

Ribbon是Spring Cloud内置的负载均衡组件，主要功能包括：
- 服务发现
- 服务选择规则
- 服务监听
- 服务重试机制

默认提供的负载均衡策略有：
- **RoundRobinRule**：轮询策略（默认）
- **RandomRule**：随机策略
- **RetryRule**：重试策略
- **WeightedResponseTimeRule**：权重策略
- **BestAvailableRule**：最低并发策略

### 2. Feign 负载均衡

Feign是一个声明式HTTP客户端，集成了Ribbon。使用@FeignClient注解可以定义一个接口，然后通过接口调用远程服务，底层使用Ribbon进行负载均衡。

在本项目中，通过ProviderService接口使用Feign调用Provider服务的API。

## 测试负载均衡

本项目提供了两种方式测试负载均衡：

### 1. 启动多个Provider实例

```bash
# 启动第一个Provider实例（端口8001）
java -jar eureka-provider.jar

# 启动第二个Provider实例（端口8003）
java -jar eureka-provider.jar --spring.profiles.active=instance2
```

### 2. 通过Consumer测试负载均衡

项目提供了以下REST接口用于测试负载均衡：

1. **查看所有Provider实例**：
   ```
   GET http://localhost:8002/consumer/instances
   ```

2. **测试Feign负载均衡**（连续调用10次，观察端口变化）：
   ```
   GET http://localhost:8002/consumer/feign-lb
   ```

3. **测试Ribbon负载均衡**（使用LoadBalancerClient选择服务）：
   ```
   GET http://localhost:8002/consumer/ribbon-lb
   ```

4. **查看当前负载均衡策略**：
   ```
   GET http://localhost:8002/consumer/lb-config
   ```

## 自定义负载均衡策略

本项目实现了两种自定义负载均衡策略的方法：

### 1. 静态配置（通过配置类）

通过创建Ribbon配置类来自定义负载均衡策略。这种方式在应用启动时生效，之后不能修改。

```java
// 注意：此配置类不能放在@ComponentScan扫描的包下，否则会被所有Ribbon客户端共享
@Configuration
public class RibbonConfig {
    @Bean
    public IRule ribbonRule() {
        // 使用随机策略替代默认的轮询策略
        return new RandomRule();
    }
}
```

然后在启动类或配置类上使用`@RibbonClient`注解指定配置：

```java
@Configuration
@RibbonClient(name = "eureka-provider", configuration = RibbonConfig.class)
public class CustomRibbonClientConfig {
    // 这个类不需要任何方法，只是用来标记配置
}
```

### 2. 动态配置（通过REST API）

本项目提供了REST API来动态切换负载均衡策略：

1. **切换负载均衡策略**：
   ```
   GET http://localhost:8002/ribbon/rule/{serviceName}/{ruleType}
   ```
   
   例如，将eureka-provider服务切换为随机策略：
   ```
   GET http://localhost:8002/ribbon/rule/eureka-provider/random
   ```
   
   支持的策略类型有：
   - random (RandomRule)
   - roundrobin (RoundRobinRule)
   - retry (RetryRule)
   - response (WeightedResponseTimeRule)
   - bestavailable (BestAvailableRule)
   - zone (ZoneAvoidanceRule)

2. **查看当前配置的规则**：
   ```
   GET http://localhost:8002/ribbon/rules
   ```

## 注意事项

1. 负载均衡是在客户端(Consumer)实现的，不是在服务端
2. Eureka的负载均衡默认使用Ribbon实现
3. 服务实例必须有相同的serviceId，才能进行负载均衡
4. 如果只有一个Provider实例，负载均衡也会正常工作，只是始终选择同一个实例
5. 自定义Ribbon配置类不能放在主应用上下文的@ComponentScan扫描范围内，否则会被所有的Ribbon客户端共享，产生意外的结果 