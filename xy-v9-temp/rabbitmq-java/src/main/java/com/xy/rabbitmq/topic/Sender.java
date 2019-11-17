package com.xy.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    //简单队列
    private final static String EXCHANGE_NAME = "topic_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1.连接MQ服务器
        ConnectionFactory factory = new ConnectionFactory();
        factory.setVirtualHost("my_vhost");
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setHost("192.168.0.100");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");


        channel.basicPublish(EXCHANGE_NAME, "nba.add", null, "加入湖人".getBytes());
        channel.basicPublish(EXCHANGE_NAME, "cba.add", null, "周琦宣布加盟广东宏远".getBytes());
        channel.basicPublish(EXCHANGE_NAME, "cba.laker.guanjun", null, "湖人总冠军".getBytes());


        System.out.println("消息发送完毕");


    }
}
