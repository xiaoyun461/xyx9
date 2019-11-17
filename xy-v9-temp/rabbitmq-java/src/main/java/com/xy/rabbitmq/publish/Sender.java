package com.xy.rabbitmq.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    //简单队列
    private final static String EXCHANGE_NAME = "fanout_exchange";

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

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");


        for (int i = 0; i < 10; i++) {
            String msg = "第" + i + "次学习打卡";
            channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
        }
        System.out.println("消息发送完毕");


    }
}
