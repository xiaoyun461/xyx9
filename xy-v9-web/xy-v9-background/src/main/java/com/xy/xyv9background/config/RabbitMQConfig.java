package com.xy.xyv9background.config;

import com.xy.v9.common.constant.RabbitMQConstant;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange getTopicExchange() {
        return new TopicExchange(RabbitMQConstant.BACKGROUND_EXCHANGE);
    }
}
