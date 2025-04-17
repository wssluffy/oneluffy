# Spring Cloud Eureka 微服务示例

本项目是一个基于Spring Cloud Eureka的微服务示例，包含三个模块：

## 1. Eureka Server (服务注册中心)
- **端口**：8761
- **功能**：作为服务注册和发现中心
- **访问地址**：http://localhost:8761
- **Actuator地址**：http://localhost:8761/actuator

## 2. Eureka Provider (服务提供者)
- **端口**：8001 (实例1) / 8003 (实例2)
- **功能**：提供一个简单的服务API
- **控制器**：ProviderController
- **API地址**：
  - /hello?name=xxx
  - /instance-info (返回实例信息，用于观察负载均衡)
- **示例**：http://localhost:8001/hello?name=oneluffy
- **Actuator地址**：
  - 服务信息：http://localhost:8001/actuator/info
  - 健康状态：http://localhost:8001/actuator/health
  - 所有端点：http://localhost:8001/actuator

## 3. Eureka Consumer (服务消费者)
- **端口**：8002
- **功能**：通过Feign调用Provider提供的服务
- **Feign客户端**：ProviderService
- **API地址**：
  - /consumer/hello?name=xxx (基本调用)
  - /consumer/feign-lb (测试Feign负载均衡)
  - /consumer/ribbon-lb (测试Ribbon负载均衡)
  - /consumer/instances (查看所有Provider实例)
  - /consumer/lb-config (查看当前负载均衡配置)
  - /ribbon/rule/{serviceName}/{ruleType} (动态切换负载均衡策略)
  - /ribbon/rules (查看当前配置的负载均衡规则)
- **示例**：http://localhost:8002/consumer/hello?name=oneluffy
- **Actuator地址**：http://localhost:8002/actuator

## 使用方法
1. 首先启动 Eureka Server
2. 然后启动 Eureka Provider (可以启动多个实例进行负载均衡测试)
   ```
   # 启动第一个实例 (端口8001)
   java -jar eureka-provider.jar
   
   # 启动第二个实例 (端口8003)
   java -jar eureka-provider.jar --spring.profiles.active=instance2
   ```
3. 最后启动 Eureka Consumer
4. 访问接口测试负载均衡：http://localhost:8002/consumer/feign-lb
5. 通过 http://localhost:8002/ribbon/rule/eureka-provider/random 动态切换负载均衡策略

## 项目结构
```
oneluffy
├── eureka-server        # 服务注册中心
│   └── docs
│       └── load-balancing.md  # 负载均衡说明文档
├── eureka-provider      # 服务提供者
└── eureka-consumer      # 服务消费者（使用Feign调用Provider）
    ├── src/main/java/com/oneluffy/consumer
    │   ├── config
    │   │   ├── CustomRibbonClientConfig.java  # Ribbon客户端配置
    │   │   └── DynamicRibbonConfiguration.java  # 动态切换负载均衡策略
    │   └── feign
    │       └── ProviderService.java  # Feign接口
    └── src/main/java/com/oneluffy/ribbonconfig
        └── RibbonConfig.java  # 自定义负载均衡策略
```

## 技术栈
- Spring Boot 3.2.3
- Spring Cloud 2023.0.0
- Spring Cloud Netflix Eureka
- Spring Cloud OpenFeign
- Spring Boot Actuator
- Ribbon (负载均衡)

## 负载均衡
Eureka的负载均衡是通过Ribbon实现的，采用客户端负载均衡机制：
1. 多个Provider服务实例注册到Eureka Server
2. Consumer从Eureka Server获取所有Provider实例列表
3. 当Consumer调用Provider服务时，Ribbon会根据负载均衡策略选择一个Provider实例
4. 默认采用轮询策略，可以自定义不同的负载均衡策略

本项目实现了两种配置负载均衡策略的方式：
1. **静态配置**：通过RibbonConfig配置类和@RibbonClient注解，在应用启动时生效
2. **动态配置**：通过REST API在运行时动态修改负载均衡策略

详细信息请参考 [负载均衡文档](./docs/load-balancing.md)

## Actuator端点

所有服务都配置了以下Actuator端点：

| 端点        | 描述                 | URL示例                      |
|------------|---------------------|------------------------------|
| /info      | 应用信息             | http://localhost:8001/actuator/info |
| /health    | 健康状态             | http://localhost:8001/actuator/health |
| /metrics   | 指标信息             | http://localhost:8001/actuator/metrics |
| /env       | 环境变量             | http://localhost:8001/actuator/env |
| /beans     | Spring Bean列表      | http://localhost:8001/actuator/beans |
| /mappings  | 所有HTTP映射         | http://localhost:8001/actuator/mappings |

## 注意事项
- 确保先启动Eureka Server，再启动其他服务
- Provider和Consumer都需要向Eureka Server注册
- Consumer通过Feign客户端实现对Provider服务的调用
- 所有服务都配置了Actuator，可以通过/actuator访问
- 测试负载均衡需要启动多个Provider实例
- 自定义Ribbon配置类不能放在主应用扫描包下，否则会被所有Ribbon客户端共享

## 示例流程
1. 启动Eureka Server后，可以访问http://localhost:8761查看注册中心
2. 启动Provider后，在Eureka控制台可以看到Provider已注册
3. 启动Consumer后，在Eureka控制台可以看到Consumer已注册
4. 访问http://localhost:8002/consumer/hello?name=oneluffy测试服务调用
5. 启动第二个Provider实例后，访问http://localhost:8002/consumer/feign-lb测试负载均衡
6. 观察返回结果中的端口号变化，验证负载均衡效果
7. 访问http://localhost:8002/ribbon/rule/eureka-provider/random切换负载均衡策略为随机
8. 再次访问http://localhost:8002/consumer/feign-lb观察负载均衡效果的变化 