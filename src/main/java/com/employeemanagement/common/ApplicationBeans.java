package com.employeemanagement.common;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class ApplicationBeans {

    public static final String QUEUE_NAME = "notificationQueue";

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");  // Use your mail server
        javaMailSender.setPort(587);  // Port 587 for TLS, 465 for SSL
        javaMailSender.setUsername("shubham.tupe2597@gmail.com");
        javaMailSender.setPassword("exkk vemz bzbk gxfc");

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        return javaMailSender;
    }
}
