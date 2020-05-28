package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "globalFallbackMethod")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/consumer/hystrix/ok/{id}")
    public String getHystrixOk(@PathVariable("id") Integer id){
        return orderService.payment_ok(id);
    }

    @GetMapping("/consumer/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod= "fallbackMethod" ,commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
//    })
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    })
    public String getHystrixTimeout(@PathVariable("id") Integer id){
        return orderService.payment_timeout(id);
    }

    public String fallbackMethod(Integer id){
        return "80消费者超过2秒，超时了, id = "+id;
    }

    //全局默认降级方法
    //不能携带参数
    public String globalFallbackMethod(){
        return "全局异常处理信息，请稍后再试";
    }
}
