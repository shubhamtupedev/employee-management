package com.employeemanagement.utility;

import com.employeemanagement.common.ApplicationBeans;
import com.employeemanagement.entity.AsyncAudit;
import com.employeemanagement.repository.AsyncAuditRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

@Service
public class NotificationConsumer {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private AsyncAuditRepository asyncAuditRepository;

    @RabbitListener(queues = ApplicationBeans.QUEUE_NAME)
    public void processNotification(HashMap<String, String> notificationData) {
        String email = notificationData.get("personalEmail");
        String phoneNumber = notificationData.get("phoneNumber");
        String auditId = notificationData.get("auditId");
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(email);
            simpleMailMessage.setFrom("shubham.tupe2597@gmail.com");
            simpleMailMessage.setSubject("Registration successfull");
            simpleMailMessage.setText("Welcome to xyz. your registration successfully done in xyz company");
            javaMailSender.send(simpleMailMessage);
            updateAsyncAuditTransaction(null, Long.parseLong(auditId));

        } catch (Exception e) {
            updateAsyncAuditTransaction(e.getMessage(), Long.parseLong(auditId));
        }
    }

    private void updateAsyncAuditTransaction(String message, Long auditId) {
        Optional<AsyncAudit> asyncAudit = asyncAuditRepository.findById(auditId);
        if (asyncAudit.isPresent()) {
            AsyncAudit audit = asyncAudit.get();
            if (message != null) {
                audit.setStatus("E");
                audit.setErrorMsg(message);
            } else {
                audit.setStatus("P");
            }
            audit.setProcessEndTime(LocalDateTime.now());
            asyncAuditRepository.save(audit);
        }
    }
}
