# Zookeeper 服务提供者

## 项目说明
本项目是基于Spring Cloud Zookeeper的服务提供者示例，提供基础的REST接口服务，并注册到Zookeeper进行服务治理。

## 技术栈
- Spring Boot
- Spring Cloud
- Spring Cloud Zookeeper
- Spring Boot Actuator
- Lombok

## 项目结构
```
zookeeper-server
├── src/main/java
│   └── com.oneluffy.zookeeper
│       ├── ZookeeperServerApplication.java    // 启动类
│       └── controller                         // 控制器层
│           └── ServerController.java          // 服务提供者控制器
└── pom.xml                                   // 项目依赖
```

## 核心功能
1. **服务注册**
   - 通过`@EnableDiscoveryClient`注解启用服务注册
   - 自动注册到Zookeeper注册中心
   - 支持健康检查

2. **REST接口**
   - `/hello`: 返回服务实例信息
   - `/health`: 健康检查接口

## 配置说明
主要配置项：
```yaml
spring:
  cloud:
    zookeeper:
      connect-string: localhost:2181    # Zookeeper连接地址
      discovery:
        enabled: true                   # 启用服务发现
        register: true                  # 启用服务注册
        root: /services                 # 服务根节点
        prefer-ip-address: true         # 使用IP地址注册
```

## 使用方法
1. **环境准备**
   - 安装并启动Zookeeper服务（默认端口2181）
   - 确保Java 17环境已安装

2. **启动服务**
   ```bash
   mvn spring-boot:run
   ```

3. **多实例部署**
   ```bash
   # 使用不同端口启动多个实例
   mvn spring-boot:run -Dserver.port=8081
   mvn spring-boot:run -Dserver.port=8083
   mvn spring-boot:run -Dserver.port=8084
   ```

4. **接口测试**
   - 访问服务：`http://localhost:8081/hello`
   - 健康检查：`http://localhost:8081/health`
   - Actuator监控：`http://localhost:8081/actuator`

## 注意事项
1. 确保Zookeeper服务正常运行
2. 多实例部署时需要配置不同的端口
3. 检查服务端口是否被占用

## 开发建议
1. 使用Actuator监控服务状态
2. 通过Zookeeper客户端查看服务注册情况
3. 建议使用Postman等工具测试接口

## 问题排查
如果遇到服务注册失败，请检查：
1. Zookeeper服务是否正常运行
2. 连接地址是否正确
3. 网络连接是否正常
4. 端口是否被占用 

## ZooKeeper 安装

1. **下载 ZooKeeper**
   - 从 [Apache ZooKeeper 官网](https://zookeeper.apache.org/releases.html) 下载最新版本的ZooKeeper。

2. **解压缩**
   - 将下载的压缩包解压到您选择的目录。

3. **配置 ZooKeeper**
   - 在解压后的目录中，找到 `conf` 文件夹，复制 `zoo_sample.cfg` 文件并重命名为 `zoo.cfg`。
   - 编辑 `zoo.cfg` 文件，设置数据目录和其他配置：
     ```properties
     tickTime=2000
     dataDir=/path/to/zookeeper/data
     clientPort=2181
     maxClientCnxns=60
     ```

4. **启动 ZooKeeper**
   - 在命令行中，进入到ZooKeeper的解压目录，运行以下命令启动ZooKeeper：
     ```bash
     bin/zkServer.sh start
     ```

5. **验证 ZooKeeper 是否正常运行**
   - 使用以下命令检查ZooKeeper的状态：
     ```bash
     bin/zkServer.sh status
     ```

6. **停止 ZooKeeper**
   - 使用以下命令停止ZooKeeper：
     ```bash
     bin/zkServer.sh stop
     ```

## ZooKeeper 集群处理

1. **集群配置**
   - 在 `zoo.cfg` 文件中，添加以下配置以设置集群：
     ```properties
     server.1=192.168.1.1:2888:3888
     server.2=192.168.1.2:2888:3888
     server.3=192.168.1.3:2888:3888
     ```
   - 其中，`192.168.1.1`、`192.168.1.2` 和 `192.168.1.3` 是各个ZooKeeper节点的IP地址。

2. **启动集群**
   - 在每个节点上，使用相同的 `zoo.cfg` 配置文件启动ZooKeeper。

3. **验证集群状态**
   - 使用 `zkCli.sh` 连接到任意一个ZooKeeper节点，执行 `stat` 命令查看集群状态。
