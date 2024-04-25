package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.requests.TaxRequest;

@RestController
public class TaxController {
    @PostMapping("/tax")
    public String postCalculate(@RequestBody TaxRequest entity) {
        float taxAmount = entity.getTaxableIncome() * entity.getTaxableRate();
        return "Your taxes are: $" + String.valueOf(taxAmount);
    }
}
