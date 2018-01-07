package com.sam.cpg.amex;

import com.sam.cpg.AuthenticationRequest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
public class AmexProcessor {

    public void consume(Message<?> message){

        Object obj = message.getPayload();

        System.out.println(obj.getClass());

        //System.out.println(request.getPayorName());
    }
    public void onMessage(AuthenticationRequest request){

        System.out.println(request.getPayorName());
    }
    @StreamListener(Sink.INPUT)
    public void process(AuthenticationRequest request){
        System.out.println(request.getPayorName());
    }
}
