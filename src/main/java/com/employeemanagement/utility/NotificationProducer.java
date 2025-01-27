package com.employeemanagement.utility;

import com.employeemanagement.common.ApplicationBeans;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationProducer {

    private RabbitTemplate rabbitTemplate;

    public NotificationProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNotification(Map<String, String> notificationData) {
        rabbitTemplate.convertAndSend(ApplicationBeans.QUEUE_NAME, notificationData);
    }

}
