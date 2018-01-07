package com.sam.cpg.amex;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;


@SpringBootApplication
//@Configuration
public class Application {

    @Value("${rabbitmq.uri}")
    private String rabbitUri;

    @Value("${rabbitmq.request-queue}")
    private String requestQueue;

    public static void main(String...args){
        SpringApplication.run(Application.class, args);
    }

    /*
    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUri(rabbitUri);
        return factory;
    }
    //@Bean
    public IntegrationFlow amqpInbound(ConnectionFactory factory, AmexProcessor processor){

        return IntegrationFlows
                .from(Amqp.inboundAdapter(factory, requestQueue))
                .handle(processor::consume)
                .get();
    }

    @Bean
    public Queue requestQueue(){
        return new Queue(requestQueue, false);
    }
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("cpg-exchange");
    }

    @Bean
    public Binding requestBindind(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(requestQueue);
    }
    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory factory, MessageListenerAdapter adapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setQueueNames(requestQueue);
        container.setMessageListener(adapter);
        return container;
    }
    @Bean
    public MessageListenerAdapter adapter(AmexProcessor processor){
        return new MessageListenerAdapter(processor, "onMessage");
    }
    */
}
