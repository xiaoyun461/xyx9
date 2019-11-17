package com.xy.rabbitmqspringboot.publish;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Recv {

    @RabbitListener(queues = "springboot-fanout-queue-1")
    public void process1(String msg) {
        System.out.println("接收One的消息为：" + msg);
    }

//    @RabbitListener(queues = " springboot-fanout-queue-2")
//    public void process2(String msg) {
//        System.out.println("接收Two的消息为：" + msg);
//    }
}
