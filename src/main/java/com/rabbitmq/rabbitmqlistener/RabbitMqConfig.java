package com.rabbitmq.rabbitmqlistener;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    private static final String MY_QUEUE = "MyQueue";
    private static final String MY_ROUTED_QUEUE = "RoutedQueue";

    @Bean
    Queue myQueue(){
        return new Queue(MY_QUEUE, true);
    }

    @Bean
    Exchange myTopicExchange(){
        return ExchangeBuilder.topicExchange("MyTopicExchange")
                .autoDelete()
                .durable(true)
                .build();
    }

    @Bean
    Binding binding(){
        //return new Binding(MY_QUEUE, Binding.DestinationType.QUEUE, "MyTopicExchange", "topic", null);

        return BindingBuilder
                .bind(myQueue())
                .to(myTopicExchange())
                .with("topic")
                .noargs();
    }

    @Bean
    Queue myRoutedQueue(){
        return new Queue(MY_ROUTED_QUEUE, true);
    }

    @Bean
    ConnectionFactory connectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }

    @Bean
    MessageListenerContainer messageListenerContainer(){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(myRoutedQueue());
        simpleMessageListenerContainer.setQueues(myQueue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMqMessageListener());
        return simpleMessageListenerContainer;
    }
}
