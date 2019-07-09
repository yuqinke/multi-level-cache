### SringCloud微服务架构设计

- 基于Spring Boot+Spring Cloud+MyBatis动态渲染系统
  - 动态渲染系统实现高并发高可用架构
    - （1）依赖服务 -> MQ -> 动态渲染服务 -> 多级缓存
      （2）负载均衡 -> 分发层nginx -> 应用层nginx -> 多级缓存
      （3）多级缓存 -> 数据直连服务
    - 1.基于Eureka作为服务注册与发现中心，Fegion声明式调用、Ribbon负载均衡、Hystrix资源隔离、熔断降级
      2.四级缓存架构，Nginx+Redis从集群+数据直连服务（Jvm缓存）+Redis主集群
      3.Nginx+redis从集群采用双机房部署，防止服务宕机不可用
      4.高可用设计：多链路降级策略，本机房从集群 -> 主集群 -> 直连
      5.全链路隔离：基于hystrix的依赖调用资源隔离，限流，熔断，降级
      6.动态渲染服务+rabbitMQ实现依赖服务异步调用，解耦合、削峰
      7.RabbitMQ优化，实现消息去重
      8.基于zookeeper实现分布式锁，防止并发冲突
- 基于Spring Boot+Spring Cloud+MyBatis实现OneService系统
  - OneService系统：依赖服务接口的统一代理服务
    - 请求统一路由到eshop-one-service代理[eshop-inventory-service](https://github.com/yuqinke/multi-level-cache/tree/master/eshop-inventory-service)和[eshop-price-service](https://github.com/yuqinke/multi-level-cache/tree/master/eshop-price-service)  
    - 请求采用Ajax异步加载，采用内存队列mysql和redis双写一致性方案
    - 实现缓存雪崩与缓存穿透预防和解决