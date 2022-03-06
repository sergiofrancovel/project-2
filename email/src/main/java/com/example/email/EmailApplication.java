package com.example.email;

import com.example.email.services.EmailServiceIntImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailApplication {

    @Autowired
    private EmailServiceIntImpl service;

    /**
     * g35qreqgerg
     * @param args qerhgqetg
     */

    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class, args);
    }
    
}
