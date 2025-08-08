# trans-sfm 理财代销SAAS系统

## 项目概述

trans-sfm 是一个基于微服务架构的理财代销SAAS系统，支持批量和在线交易处理。该系统包含多个子系统模块，通过Nacos作为注册和配置中心，实现服务的统一管理和调度。

## 系统架构

```
trans-sfm-client-online -> Nginx -> trans-sfm-gateway网关 -> trans-sfm-sa-online -> trans-sfm-ta-online
```

### 核心组件

1. **trans-sfm-client-online**: 客户端服务HTTP请求
2. **trans-sfm-gateway**: API网关，负责请求路由、限流和鉴权
3. **trans-sfm-sa-online**: SA（Service Agent）服务，核心业务处理模块
4. **trans-sfm-ta-online**: TA（Transfer Agent）服务，交易处理模块
5. **Nacos**: 服务注册与配置中心
6. **Redis**: 限流组件
7. **Nginx**: 反向代理服务器

### 调用流程

1. trans-sfm-client-online发起请求到Nginx
2. Nginx将请求转发到trans-sfm-gateway网关
3. 网关根据路由规则将请求分发到trans-sfm-sa-online
4. trans-sfm-sa-online处理业务逻辑并调用trans-sfm-ta-online
5. trans-sfm-ta-online处理交易相关操作
6. 响应按原路径返回给trans-sfm-client-online

## 技术栈

- **核心框架**: Spring Boot 2.7.0, Spring Cloud 2021.0.3
- **服务治理**: Nacos 2021.0.1.0
- **API网关**: Spring Cloud Gateway
- **服务调用**: OpenFeign
- **限流组件**: Redis
- **数据库**: MySQL
- **ORM框架**: MyBatis-Plus
- **分库分表**: ShardingSphere-JDBC
- **连接池**: Druid
- **构建工具**: Maven 3.x
- **日志管理**: Logback

## 服务配置

### Nacos配置

所有服务都配置了Nacos作为注册中心:

```yaml
spring:
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
```

### 端口分配

- trans-sfm-gateway: 8080
- trans-sfm-sa-online: 8081
- trans-sfm-client-online: 8082
- trans-sfm-ta-online: 8083

## 核心功能

### 网关功能

1. **路由转发**: 根据路径将请求转发到对应的服务
2. **限流控制**: 基于Redis实现的令牌桶算法限流
3. **权限验证**: 对特定接口进行鉴权检查
4. **负载均衡**: 通过`lb://`实现服务间负载均衡

### 限流策略

- 基于路径的限流策略
- `/api/account/openPersonAccount`接口: 5请求/秒，突发容量10
- 其他接口: 10请求/秒，突发容量20

### 鉴权机制

- 对开户相关接口进行鉴权检查
- 验证请求头中的`Authorization`和`X-API-Key`字段
- 未通过鉴权的请求返回401状态码

## 部署说明

### 环境要求

- JDK 1.8 或更高版本
- Maven 3.x
- MySQL 5.7 或更高版本
- Redis
- Nacos服务
- Nginx

### 启动顺序

1. 启动Nacos服务注册中心
2. 启动Redis服务
3. 启动MySQL数据库并执行初始化脚本
4. 按以下顺序启动服务:
   - trans-sfm-ta-online
   - trans-sfm-sa-online
   - trans-sfm-client-online
   - trans-sfm-gateway

### 构建命令

```bash
# 在每个服务目录下执行
mvn clean install

# 启动服务
mvn spring-boot:run
```

## 项目结构

```
trans-sfm/
├── trans-sfm-client-online/     # 客户端服务
├── trans-sfm-gateway/           # API网关
├── trans-sfm-sa-online/         # SA服务
├── trans-sfm-ta-online/         # TA服务
├── trans-sfm-client-batch/      # 客户端批处理
├── trans-sfm-sa-batch/          # SA批处理
├── trans-sfm-ta-batch/          # TA批处理
├── trans-sfm-transfer-file/     # 文件传输服务
├── trans-sfm-transfer-lcd/      # LCD传输服务
└── trans-sfm-pub/               # 公共模块
```

## 安全特性

1. **服务间通信**: 通过Nacos实现服务发现和负载均衡
2. **接口鉴权**: 对敏感接口进行鉴权验证
3. **访问限流**: 防止恶意请求和过载访问
4. **链路追踪**: 服务间通信包含请求ID用于追踪

## 扩展性设计

1. **微服务架构**: 各服务独立部署和扩展
2. **分库分表**: 支持大数据量处理
3. **配置中心**: 统一配置管理，支持动态更新
4. **负载均衡**: 支持服务多实例部署

## 维护说明

1. 定期检查Nacos服务状态
2. 监控Redis性能和内存使用情况
3. 检查各服务日志，及时发现和处理异常
4. 定期备份数据库