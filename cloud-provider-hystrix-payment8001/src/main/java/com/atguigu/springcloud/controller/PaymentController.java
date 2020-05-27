package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.logging.Logger;

@RestController

public class PaymentController {
    @Resource
    PaymentService service;

    @Value("${server.port}")
    String port;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String payment_ok(@PathVariable("id") Integer id){
        String res = service.paymentInfo_OK(id);
        System.out.println(res);
        return res;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String payment_timeout(@PathVariable("id") Integer id){
        String res = service.paymentInfo_TimeOut(id);
        System.out.println(res);
        return res;
    }
}
