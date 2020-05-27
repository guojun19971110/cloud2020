package com.atguigu.spingcloud.cotroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/consul")
    @ResponseBody
    public String paymentConsul(){
        return "springclud with consul"+serverPort+" "+ UUID.randomUUID().toString();
    }
}
