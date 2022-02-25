package com.example.email.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceIntImpl implements EmailServiceInt {

    private JavaMailSender javaMailSender;
    @Autowired
    public EmailServiceIntImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleMessageForOrder(String toEmail, String emailBody, String subject) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("reimbursementspring@gamil.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(emailBody);

        javaMailSender.send(message);

        System.out.println("Mail send...");

    }

}