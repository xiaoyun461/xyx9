package com.xy.rabbitmqspringboot;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqSpringbootApplication.class, args);
    }

}
