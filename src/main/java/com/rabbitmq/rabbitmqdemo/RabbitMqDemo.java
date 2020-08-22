package com.rabbitmq.rabbitmqdemo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMqDemo implements CommandLineRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static void main(String args[]){
        SpringApplication.run(RabbitMqDemo.class, args);
    }

    public void run(String... args) throws Exception {
        myfirstMessage();
        sendMessageTopic();
    }

    private void myfirstMessage(){
        rabbitTemplate.convertAndSend("test.Exchange", "testRouting","Hello from my first message!");
    }

    private void sendMessageTopic(){
        SimpleMessage simpleMessage = new SimpleMessage();
        simpleMessage.setName("FirstMessage");
        simpleMessage.setDescription("simpleDescription");

        rabbitTemplate.convertAndSend("MyTopicExchange", "topic", simpleMessage);
    }

}
