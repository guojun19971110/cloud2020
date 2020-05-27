package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderConsulController {
    @Resource
    private RestTemplate restTemplate;

    private static final String INVOKE_URL="http://cloud-provider-payment";

    @GetMapping("/orderConsul")
    public String paymentInfo(){
        String res = restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
        return res;
    }
}
