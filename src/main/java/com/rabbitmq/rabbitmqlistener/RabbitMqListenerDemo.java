package com.rabbitmq.rabbitmqlistener;

import com.rabbitmq.rabbitmqdemo.SimpleMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMqListenerDemo {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static void main(String args[]){
        SpringApplication.run(RabbitMqListenerDemo.class, args);
    }

}
