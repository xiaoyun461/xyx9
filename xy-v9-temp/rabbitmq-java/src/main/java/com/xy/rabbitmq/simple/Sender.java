package com.xy.rabbitmq.simple;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    //简单队列
    private final static String QUEUE_NAME = "simple_queue";

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
        //durable :持久化,
        //exclusive:排他件
        //autoDelete： 自动删除
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        String msg = "采用rabbitMQ第一次发送消息";
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        System.out.println("消息发送完毕");


    }
}
