package com.cookie.wash.service;

import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * author : cxq
 * Date : 2019/4/28
 */
@Service
public class Consumer {

    @Autowired
    private ConnectionFactory factory ;

    public  void consumerMsg(String queueName ) throws IOException, TimeoutException {
        /* 创建连接 */
        Connection connection = factory.newConnection();
        /* 创建信道 */
        Channel channel = connection.createChannel();

        // 声明一个队列：名称、持久性的（重启仍存在此队列）、非私有的、非自动删除的
        channel.queueDeclare(queueName, true, false, false, null);

        /* 定义消费者 */
        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received the message -> " + message);
            }
        };

        // 将消费者绑定到队列，并设置自动确认消息（即无需显示确认，如何设置请慎重考虑）
        channel.basicConsume(queueName, true, consumer);
    }
}
