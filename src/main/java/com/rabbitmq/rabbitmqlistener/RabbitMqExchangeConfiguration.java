package com.rabbitmq.rabbitmqlistener;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqExchangeConfiguration {

    @Bean
    Exchange exampleExchange(){
        return new TopicExchange("ExampleExchange");
    }

    @Bean
    Exchange example2ndExchange(){
        return ExchangeBuilder.directExchange("Example2ndExchange")
                .autoDelete()
                .internal()
                .build();
    }

    @Bean
    Exchange newExchange(){
        return ExchangeBuilder.topicExchange("TopicTestExchange")
                .autoDelete()
                .durable(true)
                .internal()
                .build();
    }

    @Bean
    Exchange topic2ndTestExchange(){
        System.out.println("Logando o bean maano");
        return ExchangeBuilder.topicExchange("Topic2ndTestExchange")
                .autoDelete()
                .durable(true)
                .internal()
                .build();
    }

    @Bean
    Exchange fanoutExchange(){
        return ExchangeBuilder.fanoutExchange("FanoutTestExchange")
                .autoDelete()
                .durable(false)
                .internal()
                .build();
    }

    @Bean
    Exchange headersExchange(){
        return ExchangeBuilder.headersExchange("HeadersExchange")
                .durable(true)
                .internal()
                .ignoreDeclarationExceptions()
                .build();
    }
}
