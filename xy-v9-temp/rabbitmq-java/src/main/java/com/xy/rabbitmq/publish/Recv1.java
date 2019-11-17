package com.xy.rabbitmq.publish;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv1 {
    private final static String EXCHANGE_NAME = "fanout_exchange";

    private final static String QUEUE_NAME = "fanout_exchange_queue_1";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setVirtualHost("my_vhost");
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setHost("192.168.0.100");
        factory.setPort(5672);

        Connection connection = factory.newConnection();

        final Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");


        //设置一次接受一个消息
        channel.basicQos(1);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //处理接收到的消息信息

                String msg = new String(body, "utf-8");
                System.out.println("Recv1:接受到的消息:" + msg);
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                这里手工回复，处理消息完毕
                channel.basicAck(envelope.getDeliveryTag(), false);
            }

        };
        //autoAck:自动回复模式 true-->修改手工模式
        channel.basicConsume(QUEUE_NAME, false, consumer);


    }
}
