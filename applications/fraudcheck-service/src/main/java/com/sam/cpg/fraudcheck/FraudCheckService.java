package com.sam.cpg.fraudcheck;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FraudCheckService {

    @PostMapping
    public ResponseEntity<FraudCheckResponse> fraudcheck(@RequestBody FraudCheckRequest request){

        FraudCheckResponse response = new FraudCheckResponse();

        if(request.getPayorName().equalsIgnoreCase("Sameer")){
            response.setResponseCode(500);
        } else {
            response.setResponseCode(200);
        }


        return new ResponseEntity<FraudCheckResponse>(response, HttpStatus.OK);
    }
    @GetMapping
    public String home(){
        return "Fraud Check";
    }
}
