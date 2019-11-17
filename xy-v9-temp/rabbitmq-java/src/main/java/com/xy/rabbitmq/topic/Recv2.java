package com.xy.rabbitmq.topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv2 {
    private final static String EXCHANGE_NAME = "topic_exchange";

    private final static String QUEUE_NAME = "topic__exchange_queue_2";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setVirtualHost("my_vhost");
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setHost("192.168.0.100");
        factory.setPort(5672);

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "nba.#");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "cba.#");

        Consumer consumer = new DefaultConsumer(channel) {


            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //处理接收到的消息信息
                String msg = new String(body, "utf-8");
                System.out.println("Recv2:接受到的消息:" + msg);
            }
        };
        //autoAck:自动回复模式 true
        channel.basicConsume(QUEUE_NAME, true, consumer);


    }
}
