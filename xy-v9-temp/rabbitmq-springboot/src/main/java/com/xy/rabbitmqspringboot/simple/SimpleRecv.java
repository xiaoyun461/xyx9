package com.xy.rabbitmqspringboot.simple;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleRecv {

    @RabbitListener(queues = "springboot-simple-queue")
    public void process(String msg) {
        System.out.println("接收的消息为：" + msg);
    }
}
