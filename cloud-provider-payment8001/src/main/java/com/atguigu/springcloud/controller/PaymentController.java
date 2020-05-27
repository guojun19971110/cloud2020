package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    @ResponseBody
    public CommonResult create(@RequestBody Payment payment){
        log.info(payment.toString());
        int result=paymentService.create(payment);
        if(result>0){
            System.out.println(payment.toString());
            return new CommonResult(200,"插入数据库成功"+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    @ResponseBody
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment=paymentService.getPaymentById(id);
        log.info("查询结果***"+payment);
        if(payment!=null){
            return new CommonResult(200,"获取成功"+serverPort,payment);
        }else{
            return new CommonResult(444,"获取失败",null);
        }
    }

    @GetMapping("/payment/discover")
    public Object discover(){
        List<String> services=discoveryClient.getServices();
        for(String str:services){
            log.info(str.toString()+"-----------");
        }
        List<ServiceInstance> serviceInstanceList=discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance element:serviceInstanceList){
            log.info(element.getHost()+" "+element.getInstanceId()+" "+element.getPort()+" "+element.getUri());
        }
        return this.discoveryClient;
    }
}
