package com.cookie.wash.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * author : cxq
 * Date : 2019/4/28
 */
@Service
public class Producer {

    private final static Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private ConnectionFactory factory ;

    public void sendMsg(String queueName , String message ) throws  Exception {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            // 声明一个队列：名称、持久性的（重启仍存在此队列）、非私有的、非自动删除的
            channel.queueDeclare(queueName, true, false, false, null);
            // 发送消息，使用默认的direct交换器
            channel.basicPublish("", queueName, null, message.getBytes());


        } catch (Exception e) {
            logger.error("sendMsg fail  ");
        }finally {
            // 关闭连接通道
            channel.close();
            connection.close();
        }
    }

    public void  sendMsgTransaction(String queueName , String message ) throws  Exception {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(queueName, true, false, false, null);

            channel.txSelect();
            channel.basicPublish("", queueName, null, message.getBytes());
            channel.txCommit();


        } catch (Exception e) {
            channel.txRollback();   // 回滚
            logger.error("sendMsgTransaction fail  ");

        }finally {
            // 关闭连接通道
            channel.close();
            connection.close();
        }

    }

    public boolean sendMsgConfirm(String queueName , String message ) throws  Exception {
        boolean flag = false   ;
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(queueName, true, false, false, null);
            //生产者通过调用channel的confirmSelect方法将channel设置为confirm模式
            channel.confirmSelect();
            channel.basicPublish("", queueName, null, message.getBytes());

            if (channel.waitForConfirms()) {
               flag = true ;
            }

//              TODO 消息发送后等待Ack确认
//            channel.basicPublish(EXCHANGE_NAME, ROUTE_KEY, true,null, message.getBytes());
//            if(channel.waitForConfirms()){
//                log.info("send success");
//                //...success业务处理...
//            }else{
//                log.info("send failure");
//                //...failure业务处理...
//            }

        } catch (Exception e) {
            logger.error("sendMsgConfirm  fail  ");

        }finally {
            // 关闭连接通道
            channel.close();
            connection.close();
        }
        return  flag ;

    }

    public boolean sendMsgConfirmBatch(String queueName , String message ) throws  Exception {
        boolean flag = false   ;
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(queueName, true, false, false, null);
            //生产者通过调用channel的confirmSelect方法将channel设置为confirm模式
            channel.confirmSelect();

            //生产者通过调用channel的confirmSelect方法将channel设置为confirm模式
            channel.confirmSelect();
            for (int i = 0; i < 10; i++) {
                channel.basicPublish("", queueName, null,message.getBytes());
            }

            if (!channel.waitForConfirms()) {
                System.out.println("send message error");
            } else {
                System.out.println(" send messgae ok ...");
            }

        } catch (Exception e) {
            logger.error("sendMsgConfirm  fail  ");

        }finally {
            // 关闭连接通道
            channel.close();
            connection.close();
        }
        return  flag ;

    }

    public boolean sendMsgConfirmSync(String queueName , String message ) throws  Exception {
        boolean flag = false   ;
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(queueName, true, false, false, null);
            SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());
            //生产者通过调用channel的confirmSelect方法将channel设置为confirm模式
            channel.confirmSelect();

//              TODO  添加监听器异步处理返回结果
//            channel.addConfirmListener(new ConfirmListener() {
//                public void handleAck(long deliveryTag, boolean multiple)
//                        throws IOException {
//                    log.info("send success");
//                    //...success业务处理...
//                }
//
//                public void handleNack(long deliveryTag, boolean multiple)
//                        throws IOException {
//                    log.info("send failure");
//                    //...failure业务处理...
//                }
//            });
            while (true) {
                long nextSeqNo = channel.getNextPublishSeqNo();
                channel.basicPublish("", queueName, null, message.getBytes());
                confirmSet.add(nextSeqNo);
            }
        } catch (Exception e) {
            logger.error("sendMsgConfirmSync  fail  ");

        }finally {
            // 关闭连接通道
            channel.close();
            connection.close();
        }
        return  flag ;

    }

    public static void main(String[] args) {
        System.out.println(1<<2);
    }


}
