package com.xy.rabbitmqspringboot;

import com.xy.rabbitmqspringboot.publish.Sender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqSpringbootApplicationTests {
    @Autowired(required = false)
    private Sender sender;

    @Test
    void contextLoads() {
        sender.send("热烈庆祝springboot整合rabbitMq成功");

    }

}
