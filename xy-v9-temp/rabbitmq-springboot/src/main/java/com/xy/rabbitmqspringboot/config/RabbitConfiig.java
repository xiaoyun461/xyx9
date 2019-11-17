package com.xy.rabbitmqspringboot.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiig {

    @Bean
    public Queue getQueue() {
        return new Queue("springboot-simple-queue", true, false, false);
    }

    @Bean
    public FanoutExchange getFanoutExchange() {
        return new FanoutExchange("springboot-fanout-exchange");
    }

    @Bean
    public TopicExchange getTopicExchange() {
        return new TopicExchange("springboot-topic-exchange");
    }


    @Bean
    public Queue getQueueOne() {
        return new Queue("springboot-fanout-queue-1");
    }

    @Bean
    public Queue getQueueTwo() {
        return new Queue("springboot-fanout-queue-2");
    }

    @Bean
    public Binding getBindingOne(FanoutExchange getFanoutExchange, Queue getQueueOne) {
        return BindingBuilder.bind(getQueueOne).to(getFanoutExchange);
    }

    @Bean
    public Binding getBindingTwo(FanoutExchange getFanoutExchange, Queue getQueueTwo) {
        return BindingBuilder.bind(getQueueTwo).to(getFanoutExchange);
    }

    @Bean
    public Binding getBindingTopic(TopicExchange getTopicExchange, Queue getQueueOne) {
        return BindingBuilder.bind(getQueueOne).to(getTopicExchange).with("nba.#");
    }

}
