package com.treinetic.interviews1.service;

import com.treinetic.interviews1.dto.RegistrationStatus;
import com.treinetic.interviews1.dto.Student;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class NotificationService {
    @RabbitListener(queues = "${app.QUEUE}")
    public void adminMessageFromQueue(RegistrationStatus status) {
        System.out.println("Registration received from queue : " + status);
    }

}
