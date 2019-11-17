package com.xy.xyv9itemservice.config;

import com.xy.v9.common.constant.RabbitMQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue getQueue() {
        return new Queue(RabbitMQConstant.BACKGROUND_PRODUCT_SAVE_UPDATE_ITEM_QUEUE);
    }

    @Bean
    public TopicExchange getTopicExchange() {
        return new TopicExchange(RabbitMQConstant.BACKGROUND_EXCHANGE);
    }

    @Bean
    public Binding getBinding(Queue getQueue, TopicExchange getTopicExchange) {
        return BindingBuilder.bind(getQueue).to(getTopicExchange).with("product.add");
    }
}
