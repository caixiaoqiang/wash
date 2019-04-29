package com.cookie.wash;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * author : cxq
 * Date : 2019/4/28
 */

@Configuration
public class RabbitConfig   {

    @Bean
    public ConnectionFactory factory(@Value("${spring.rabbitmq.host}") String host ,
                                     @Value("${spring.rabbitmq.username}")String userName ,
                                     @Value("${spring.rabbitmq.password}")String password ,
                                     @Value("${spring.rabbitmq.port}")int  port ){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setUsername(userName);
        factory.setPassword(password);
        factory.setPort(port);

        return  factory ;
    }



}
