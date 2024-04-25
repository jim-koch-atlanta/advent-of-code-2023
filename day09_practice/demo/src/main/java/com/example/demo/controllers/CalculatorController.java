package com.example.demo.controllers;

import com.example.demo.requests.EquationRequest;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class CalculatorController {
    public Entry<Boolean, Integer> isAddition(String equation) {
        String regex = "^\\d+\\s?\\+\\s?\\d+$";
        if (equation.matches(regex)) {
            String[] operands = equation.split("\\s*\\+\\s*");
            Integer i1 = Integer.parseInt(operands[0].trim());
            Integer i2 = Integer.parseInt(operands[1].trim());
            return new SimpleEntry<>(true, i1 + i2);
        } else {
            return new SimpleEntry<>(false, null);
        }
    }

    public Entry<Boolean, Integer> isSubtraction(String equation) {
        String regex = "^\\d+\\s?-\\s?\\d+$";
        if (equation.matches(regex)) {
            String[] operands = equation.split("\\s*-\\s*");
            Integer i1 = Integer.parseInt(operands[0].trim());
            Integer i2 = Integer.parseInt(operands[1].trim());
            return new SimpleEntry<>(true, i1 - i2);
        } else {
            return new SimpleEntry<>(false, null);
        }
    }

    public Entry<Boolean, Integer> isMultiplication(String equation) {
        String regex = "^\\d+\\s?\\*\\s?\\d+$";
        if (equation.matches(regex)) {
            String[] operands = equation.split("\\s*\\*\\s*");
            Integer i1 = Integer.parseInt(operands[0].trim());
            Integer i2 = Integer.parseInt(operands[1].trim());
            return new SimpleEntry<>(true, i1 * i2);
        } else {
            return new SimpleEntry<>(false, null);
        }
    }

    public Entry<Boolean, Integer> isDivision(String equation) {
        String regex = "^\\d+\\s?/\\s?\\d+$";
        if (equation.matches(regex)) {
            String[] operands = equation.split("\\s*/\\s*");
            Integer i1 = Integer.parseInt(operands[0].trim());
            Integer i2 = Integer.parseInt(operands[1].trim());
            return new SimpleEntry<>(true, i1 / i2);
        } else {
            return new SimpleEntry<>(false, null);
        }
    }

    @PostMapping("/calculate")
    public String postCalculate(@RequestBody EquationRequest entity) {
        Entry<Boolean, Integer> result = isAddition(entity.getEquation());
        if (result.getKey()) {
            return "Addition: " + result.getValue();
        }

        result = isSubtraction(entity.getEquation());
        if (result.getKey()) {
            return "Subtraction: " + result.getValue();
        }

        result = isMultiplication(entity.getEquation());
        if (result.getKey()) {
            return "Multiplication: " + result.getValue();
        }

        result = isDivision(entity.getEquation());
        if (result.getKey()) {
            return "Division: " + result.getValue();
        }

        return "Calc-you-later!!!";
    }

    @GetMapping("/")
    public String getMethodName(@RequestParam(required = false, defaultValue = "World") String param) {
        return "Hello " + param + "!";
    }
    
    
}
