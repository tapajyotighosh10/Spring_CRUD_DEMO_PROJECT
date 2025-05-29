package com.exampleSpring.service.Impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailServiceImplTest {

    @Autowired
    private EmailServiceImpl emailService;

    @Test
    void testSendEmail(){
        emailService.sendEmail("tapajyotighosh252@gmail.com","Greeting to for Birthday","Happy Birthday");
    }

}