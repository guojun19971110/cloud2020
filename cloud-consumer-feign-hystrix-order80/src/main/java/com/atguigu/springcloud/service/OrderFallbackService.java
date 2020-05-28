package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class OrderFallbackService implements OrderService {
    @Override
    public String payment_ok(Integer id) {
        return "fallback implement Service";
    }

    @Override
    public String payment_timeout(Integer id) {
        return "Timeout fallback implement service";
    }
}
