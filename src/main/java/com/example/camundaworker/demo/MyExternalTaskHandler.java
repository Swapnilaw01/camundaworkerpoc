package com.example.camundaworker.demo;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ExternalTaskSubscription("hellotopic")
public class MyExternalTaskHandler implements ExternalTaskHandler {
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        String businessKey = externalTask.getBusinessKey();
        System.out.println("Received external task with business key: " + businessKey);

        // Your business logic here
        try {
            // Simulate work
            Thread.sleep(1000);

            // Complete the task
            externalTaskService.complete(externalTask);
            System.out.println("Task completed: " + externalTask.getId());

        } catch (Exception e) {
            externalTaskService.handleFailure(externalTask, e.getMessage(), "Stacktrace", 0, 0);
        }
    }
}
