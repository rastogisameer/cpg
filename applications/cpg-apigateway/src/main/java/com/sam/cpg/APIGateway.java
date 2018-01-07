package com.sam.cpg;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.Transformer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;

@RestController
@RequestMapping("/cpg")
public class APIGateway {

    @Value("${fraudcheck.url}")
    private String fraudcheckUrl;

    private RestOperations restOperations;

    @Value("${rabbitmq.request-queue}")
    private String requestQueue;

    private RabbitTemplate rabbitTemplate;

    private AuthenticationProcessor authProcessor;

    public APIGateway(RestOperations restOperations, ConnectionFactory factory, AuthenticationProcessor authProcessor){

        this.restOperations = restOperations;
        this.rabbitTemplate = new RabbitTemplate(factory);
        this.authProcessor = authProcessor;

    }

    @PostMapping("authn")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){

        FraudCheckRequest fraudcheckRequest = new FraudCheckRequest();
        fraudcheckRequest.setCreditCardNumber(request.getCreditCardNumber());
        fraudcheckRequest.setPayorName(request.getPayorName());

        ResponseEntity<FraudCheckResponse> fraudcheckRes = restOperations.postForEntity(fraudcheckUrl, fraudcheckRequest, FraudCheckResponse.class);

        FraudCheckResponse fraudcheckResponse = fraudcheckRes.getBody();

        AuthenticationResponse response = new AuthenticationResponse();

        if(fraudcheckResponse.getResponseCode() == 500){
            response.setResponseCode(400);
        } else {
            // rabbitTemplate.convertAndSend(requestQueue, request);
            System.out.println("apigateway");
            authProcessor.authenticate(request);
            response.setResponseCode(200);
        }

        return new ResponseEntity<AuthenticationResponse>(response, HttpStatus.OK);
    }

    @GetMapping
    public String home(){
        return "CPG";
    }
}
