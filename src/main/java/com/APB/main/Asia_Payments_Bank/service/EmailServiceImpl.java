package com.APB.main.Asia_Payments_Bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage(); 
            message.setFrom("japes1912@gmail.com"); // Replace with your email address
            message.setTo(to); 
            message.setSubject(subject); 
            message.setText(text);
            javaMailSender.send(message);
            System.out.println("Email sent successfully to: " + to);
        } catch (MailException e) {
            System.err.println("Error occurred while sending email to: " + to);
            e.printStackTrace();
        }
    }
}
