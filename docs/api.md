# API文档

## 服务提供者API (eureka-provider)

### 1. 基础信息

- 基础路径：`http://localhost:8001`
- 服务名称：`eureka-provider`

### 2. 接口列表

#### 2.1 Hello接口

```
GET /provider/hello
```

参数：
- `name`: String, 可选, 默认值为"world"

响应：
```json
{
    "message": "Hello, {name}!"
}
```

#### 2.2 实例信息接口

```
GET /provider/instance-info
```

响应：
```json
{
    "serviceId": "eureka-provider",
    "host": "192.168.1.100",
    "port": 8001,
    "uri": "http://192.168.1.100:8001",
    "metadata": {
        "version": "1.0.0"
    }
}
```

## 服务消费者API (eureka-consumer)

### 1. 基础信息

- 基础路径：`http://localhost:8002`
- 服务名称：`eureka-consumer`

### 2. 接口列表

#### 2.1 Hello接口（通过Feign调用）

```
GET /consumer/hello
```

参数：
- `name`: String, 可选, 默认值为"world"

响应：
```json
{
    "message": "Consumer调用结果: Hello, {name}!"
}
```

#### 2.2 Feign负载均衡测试接口

```
GET /consumer/feign-lb
```

响应：
```json
{
    "type": "Feign调用负载均衡",
    "results": [
        {
            "serviceId": "eureka-provider",
            "host": "192.168.1.100",
            "port": 8001,
            "uri": "http://192.168.1.100:8001",
            "timestamp": 1647123456789
        },
        // ... 更多调用结果
    ]
}
```

#### 2.3 直接负载均衡测试接口

```
GET /consumer/ribbon-lb
```

响应：
```json
{
    "type": "LoadBalancer负载均衡",
    "results": [
        {
            "serviceId": "eureka-provider",
            "host": "192.168.1.100",
            "port": 8001,
            "uri": "http://192.168.1.100:8001",
            "timestamp": 1647123456789
        },
        // ... 更多调用结果
    ]
}
```

#### 2.4 负载均衡配置信息接口

```
GET /consumer/lb-config
```

响应：
```json
{
    "serviceName": "eureka-provider",
    "loadBalancerClass": "RoundRobinLoadBalancer",
    "loadBalancerType": "Spring Cloud LoadBalancer",
    "algorithm": "Round Robin (轮询)"
}
```

## 监控端点 (Actuator)

所有服务都支持以下监控端点：

### 1. 健康检查

```
GET /actuator/health
```

响应：
```json
{
    "status": "UP",
    "components": {
        "diskSpace": {
            "status": "UP",
            "details": {
                "total": 250790436864,
                "free": 86142738432,
                "threshold": 10485760
            }
        },
        "ping": {
            "status": "UP"
        }
    }
}
```

### 2. 应用信息

```
GET /actuator/info
```

响应：
```json
{
    "app": {
        "name": "eureka-provider",
        "description": "Eureka Provider Service",
        "version": "1.0.0"
    },
    "serviceDetails": {
        "serviceType": "provider",
        "company": "oneluffy",
        "status": "running"
    }
}
```

### 3. 指标信息

```
GET /actuator/metrics
```

响应：
```json
{
    "names": [
        "jvm.memory.used",
        "jvm.memory.max",
        "http.server.requests",
        // ... 更多指标
    ]
}
```

## 错误码说明

| 错误码 | 说明 | HTTP状态码 |
|--------|------|------------|
| 200 | 成功 | 200 |
| 400 | 请求参数错误 | 400 |
| 401 | 未授权 | 401 |
| 403 | 禁止访问 | 403 |
| 404 | 资源不存在 | 404 |
| 500 | 服务器内部错误 | 500 |

## 安全说明

1. 所有接口调用需要在header中携带token（如果启用了安全认证）
2. 建议使用HTTPS进行通信
3. 敏感数据传输时需要加密 