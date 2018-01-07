package com.sam.cpg;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class)
public class AuthenticationProcessor {

    private Source source;

    public AuthenticationProcessor(Source source){
        this.source = source;
    }

    //@StreamListener(Source.OUTPUT)
    public void authenticate(AuthenticationRequest request){
        source.output().send(MessageBuilder.withPayload(request).build());
        System.out.println("processor send");
        //return request;
    }
}