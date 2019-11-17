package com.xy.rabbitmqspringboot.publish;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg) {
        rabbitTemplate.convertAndSend("springboot-topic-exchange", "nba.add", msg);
        System.out.println("发送消息成功");
    }
}
