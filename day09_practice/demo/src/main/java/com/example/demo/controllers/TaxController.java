package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.requests.EquationRequest;
import com.example.demo.requests.TaxRequest;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class TaxController {
    @Autowired
    private EurekaClient discoveryClient;

    @PostMapping("/tax")
    public String postCalculate(@RequestBody TaxRequest entity) {
        // Tax "microservice" will utilize calculator "microservice".
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("calculator", false);
        String calculatorURL = instance.getHomePageUrl() + "calculate";

        // Generate our equation request.
        String equation = String.format("%d * %.4f", entity.getTaxableIncome(), entity.getTaxableRate());
        EquationRequest request = new EquationRequest();
        request.setEquation(equation);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(calculatorURL, request, String.class);
    }
}
