package com.example.camundaworker.demo.service;

import org.camunda.bpm.client.task.ExternalTask;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {


    public void processBusinessLogic(ExternalTask externalTask) {
        // DB operation
        int input = Integer.parseInt(externalTask.getVariable("input"));
        // Save to DB, call other services, etc.
        // If this fails, the task won't be completed

        if(input < 10){
            throw new RuntimeException("Invoice not found: " + input);
        }
        System.out.println("Processing invoice " + input);
    }
}
