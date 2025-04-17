# 部署指南

## 环境要求

### 1. 基础环境

- JDK 17+
- Maven 3.6+
- MySQL 8.0+
- RabbitMQ 3.8+
- 操作系统：Windows/Linux/MacOS

### 2. 网络要求

- 服务器间网络互通
- 开放必要端口：
  - Eureka Server: 8761
  - Provider: 8001
  - Consumer: 8002
  - MySQL: 3306
  - RabbitMQ: 5672, 15672

## 部署步骤

### 1. 数据库配置

1. 创建数据库
```sql
CREATE DATABASE oneluffy DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. 创建用户并授权
```sql
CREATE USER 'oneluffy'@'%' IDENTIFIED BY 'oneluffy';
GRANT ALL PRIVILEGES ON oneluffy.* TO 'oneluffy'@'%';
FLUSH PRIVILEGES;
```

### 2. RabbitMQ配置

1. 创建虚拟主机
```bash
rabbitmqctl add_vhost /oneluffy
```

2. 创建用户并设置权限
```bash
rabbitmqctl add_user oneluffy oneluffy
rabbitmqctl set_permissions -p /oneluffy oneluffy ".*" ".*" ".*"
```

### 3. 服务部署

#### 3.1 打包所有服务

在项目根目录执行：
```bash
mvn clean package -DskipTests
```

#### 3.2 部署Eureka Server

1. 复制jar包
```bash
cp eureka-server/target/eureka-server-1.0.0.jar /app/eureka-server/
```

2. 创建配置文件
```bash
mkdir -p /app/eureka-server/config
cp eureka-server/src/main/resources/application.yml /app/eureka-server/config/
```

3. 启动服务
```bash
nohup java -jar /app/eureka-server/eureka-server-1.0.0.jar --spring.config.location=file:/app/eureka-server/config/application.yml > /app/eureka-server/logs/eureka.log 2>&1 &
```

#### 3.3 部署Provider

1. 复制jar包
```bash
cp eureka-provider/target/eureka-provider-1.0.0.jar /app/eureka-provider/
```

2. 创建配置文件
```bash
mkdir -p /app/eureka-provider/config
cp eureka-provider/src/main/resources/application.yml /app/eureka-provider/config/
```

3. 启动服务
```bash
nohup java -jar /app/eureka-provider/eureka-provider-1.0.0.jar --spring.config.location=file:/app/eureka-provider/config/application.yml > /app/eureka-provider/logs/provider.log 2>&1 &
```

#### 3.4 部署Consumer

1. 复制jar包
```bash
cp eureka-consumer/target/eureka-consumer-1.0.0.jar /app/eureka-consumer/
```

2. 创建配置文件
```bash
mkdir -p /app/eureka-consumer/config
cp eureka-consumer/src/main/resources/application.yml /app/eureka-consumer/config/
```

3. 启动服务
```bash
nohup java -jar /app/eureka-consumer/eureka-consumer-1.0.0.jar --spring.config.location=file:/app/eureka-consumer/config/application.yml > /app/eureka-consumer/logs/consumer.log 2>&1 &
```

## 配置说明

### 1. 生产环境配置调整

#### 1.1 JVM配置

```bash
JAVA_OPTS="-Xms512m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m"
```

#### 1.2 应用配置

修改application.yml中的配置：

```yaml
spring:
  profiles:
    active: prod
  
eureka:
  client:
    serviceUrl:
      defaultZone: http://eureka1:8761/eureka/,http://eureka2:8762/eureka/
      
logging:
  file:
    path: /app/logs
  level:
    root: WARN
    com.oneluffy: INFO
```

### 2. 监控配置

#### 2.1 启用Actuator端点

```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: always
```

#### 2.2 配置日志聚合

推荐使用ELK stack进行日志聚合：

1. 安装Filebeat
2. 配置Logstash
3. 使用Elasticsearch存储
4. 使用Kibana展示

## 高可用部署

### 1. Eureka集群

部署多个Eureka Server实例，互相注册：

```yaml
eureka:
  client:
    serviceUrl:
      defaultZone: http://eureka1:8761/eureka/,http://eureka2:8762/eureka/
```

### 2. 服务多实例

每个服务部署多个实例：

```bash
# 实例1
java -jar app.jar --server.port=8001

# 实例2
java -jar app.jar --server.port=8002
```

### 3. 数据库高可用

1. 配置MySQL主从复制
2. 使用数据库连接池
3. 配置数据库监控

## 运维管理

### 1. 服务启停脚本

创建service.sh：

```bash
#!/bin/bash
APP_NAME="eureka-server"
APP_JAR="${APP_NAME}.jar"

case "$1" in
    start)
        nohup java -jar $APP_JAR > /dev/null 2>&1 &
        echo "=== start $APP_NAME ==="
        ;;
    stop)
        kill $(pgrep -f $APP_JAR)
        echo "=== stop $APP_NAME ==="
        ;;
    restart)
        $0 stop
        sleep 2
        $0 start
        echo "=== restart $APP_NAME ==="
        ;;
    *)
        echo "Usage: $0 {start|stop|restart}"
        exit 1
esac
exit 0
```

### 2. 监控脚本

创建monitor.sh：

```bash
#!/bin/bash
# 检查服务状态
curl -s http://localhost:8761/actuator/health

# 检查内存使用
free -m

# 检查磁盘使用
df -h
```

### 3. 日志清理

创建clean-logs.sh：

```bash
#!/bin/bash
# 删除30天前的日志
find /app/logs -name "*.log" -mtime +30 -exec rm -f {} \;
```

## 故障处理

### 1. 常见问题

1. 服务注册失败
   - 检查Eureka Server是否可访问
   - 检查网络连接
   - 检查配置文件

2. 负载均衡失败
   - 检查服务实例状态
   - 检查负载均衡配置
   - 查看调用日志

3. 数据库连接问题
   - 检查数据库状态
   - 检查连接池配置
   - 检查网络连接

### 2. 问题排查

1. 查看日志
```bash
tail -f /app/logs/application.log
```

2. 查看JVM状态
```bash
jstat -gcutil $(pgrep -f app.jar)
```

3. 导出线程dump
```bash
jstack $(pgrep -f app.jar) > thread.dump
``` 