# springCloud做了什么：
springcloud对比dubbo，最大的特点之一就是使用Restful的模式进行交互，dubbo是基于RPC进行通信的，Http由于大众化，所以本身协议会有点笨重，解析起来自然也比RPC要慢，这也是RPC的优势之一，但是Http具有良好的跨平台性质<br>
参考： https://blog.csdn.net/zhangweiwei2020/article/details/78672814

# springcloud微服务架构搭建:
Consul/eureka+sleuth+zipkin+Feign/Ribbon+SpringConfig+Zuul+Hystrix Dash-Board-Turbine<br>
1）Hystrix Turbine将每个服务Hystrix Dashboard数据进行了整合,Hystrix Dashboard 只能看单个应用 （负责监控 Hystrix 的熔断情况）<br>
2）nginx是做负载均衡请求转发，更多被用作负载均衡器使用的；zuul是请求转发，一般用来做网关的(路由)<br>
3）sleuth（方案）zipkin（实现） 并提供存储时许数据、可视化（链路追踪）<br>
4）Feign底层是Ribbon （负载均衡,整合了Hystrix，具有熔断的能力）

# Consul与Eureka对比：
Spring Cloud的注册中心可以由Eureka、Consul、Zookeeper、ETCD等来实现<br>
参考： https://blog.csdn.net/ZYC88888/article/details/81453647


# springCloud 参考教程：
https://www.cnblogs.com/williamjie/p/9372143.html（springboot 2.x版本)<br>
https://blog.csdn.net/hry2015/article/details/78905489(sleuth,zipkin)<br>
https://www.cnblogs.com/hyhnet/p/8097635.html(Api 网关)

# consul 作为服务中心参考教程： 
https://blog.csdn.net/forezp/article/details/70188595(单机)<br>
http://www.ymq.io/2017/11/26/spring-cloud-consul/ （单机、集群环境搭建）<br>
https://www.cnblogs.com/scode2/p/8671223.html（consul 客户端）


# 功能介绍:
## consul服务中心:
  cmd/sh控制台运行命令： consul agent -dev<br>
  访问控制台查看： http://localhost:8500<br>
  运行：consul-client-ribbon、consul-client<br>
  访问：http://127.0.0.1:8773/hi （ribbon 调用 http://127.0.0.1:8763/hi）
  
## eureka服务中心:
  运行eurekaserver、eurekaclient<br>
  或运行高可用eurekaserver-hight（application.yml切换profiles.active启动多个）、eurekaclient<br>
  访问控制台查看： http://127.0.0.1:8761 
  
## feign、负载均衡，hystrix熔断：
  运行eurekaserver、eurekaclient、service-feign <br>
  访问：http://127.0.0.1:8765/hi feign 调用 http://127.0.0.1:8762/hi）<br>
  
## ribbon、负载均衡，hystrix熔断：  
   运行eurekaserver、eurekaclient、service-ribbon <br>
   访问：http://127.0.0.1:8764/hi （ribbon 调用 http://127.0.0.1:8762/hi）
   
## HystrixDashboard查看单个服务熔断情况(启动承上): 
 HystrixDashboard 需要添加 <br>
 访问 http://localhost:8764/hystrix <br>
 输入： http://localhost:8764/actuator/hystrix.stream <br>
 在请求http://localhost:8762/actuator/hystrix.stream之前，需要随便请求一个接口，例如：http://localhost:8762/hi，否则会一直ping ping ping   
 
## turbine查看多个个服务熔断情况（集成多个HystrixDashboard）：
 运行eurekaserver、eurekaclient（可选）、service-ribbon、service-turbine <br>
 访问： http://localhost:8764/hystrix  输入： http://localhost:8771/turbine.stream
 
 
## zipkin查看服务依赖关系：
 运行eurekaserver、service-ribbon、eurekaclient <br>
 运行 java -jar zipkin-server-2.10.1-exec.jar <br>
 访问： http://localhost:8764/hi（ribbon 调用 http://127.0.0.1:8762/hi）
 访问http://localhost:9411查看效果服务依赖关系图
 

## 单个服务从服务中心获取配置文件更新：
 运行：eurekaserver、config-server、config-client <br>
 访问：http://localhost:8881/hi <br>
 接着更新github上的配置文件 <br>
 git控制台执行curl：  curl http://127.0.0.1:8881/actuator/refresh -X POST  <br>
 访问：http://localhost:8881/hi 可查看到配置已更新
 
## 多个服务从服务中心获取配置文件更新（bus）：
  安装rabbimq 或kafaka （实验未安装） <br>
  config-client配置相应参数  <br>
  访问：http://localhost:8881/hi <br>
  接着更新github上的配置文件 <br>
  git控制台执行curl：  curl http://localhost:8881/bus/refresh -X POST  <br>
  访问：http://localhost:8881/hi 可查看到配置已更新
 
## zuul网关：
  运行：eurekaserver、eurekaclient（可选）、service-zuul、service-ribbon、service-feign <br>
  访问： http://localhost:8769/api-a/hi (映射到service-ribbon) <br>
         http://localhost:8769/api-b/hi (映射到service-feign)

 
 
 
 


