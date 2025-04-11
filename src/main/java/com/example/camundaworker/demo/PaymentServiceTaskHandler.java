package com.example.camundaworker.demo;

import com.example.camundaworker.demo.service.PaymentService;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;



@Configuration
@ExternalTaskSubscription("payment")
public class PaymentServiceTaskHandler implements ExternalTaskHandler {
    @Autowired
    PaymentService paymentService;


    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

        try{
            paymentService.processBusinessLogic(externalTask);

            externalTaskService.complete(externalTask);
        }catch (Exception e){
            System.err.println("Error while processing task: " + e.getMessage());
            externalTaskService.handleBpmnError(externalTask, "DB call failed",e.getMessage());
        }
    }
}
