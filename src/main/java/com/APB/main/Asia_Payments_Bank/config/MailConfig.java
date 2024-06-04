package com.APB.main.Asia_Payments_Bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;
//config
@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.elasticemail.com");
        mailSender.setPort(2525);

        mailSender.setUsername("japes1912@gmail.com"); // Remove space after email
        mailSender.setPassword("BA98BC3E45ADFDDD40B45BBFA201332D195A"); // Replace with your SMTP password

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }
}
