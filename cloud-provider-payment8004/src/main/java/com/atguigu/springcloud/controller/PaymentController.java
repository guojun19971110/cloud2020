package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    //zk是临时节点，一段时间没收到心跳回应就删除，和eureka的自我保护不一样
    @GetMapping("/payment/zk")
    @ResponseBody
    public String paymentZk(){
        return "springclud with zookeeper"+serverPort+" "+ UUID.randomUUID().toString();
    }
}
