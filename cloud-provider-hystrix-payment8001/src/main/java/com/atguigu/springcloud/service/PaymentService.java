package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_OK,id "+id+"\t"+"O(∩_∩)O";
    }

    public String paymentInfo_TimeOut(Integer id){
        int time =3;
        try {

            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_TimeOut,id "+id+"\t"+"呜呜呜,耗时"+time+"S";
    }
}
