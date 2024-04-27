package com.karthik.emailservice;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = EmailServiceApplication.class)
class EmailServiceImplIT
{
    @Autowired
    private EmailService emailService;
    
    @Test
    public void shouldSendMail()
    {
        List<String> to = Arrays.asList("karthik.iyer97@gmail.com");
        String subject = "My First Email using google smtp";
        String body = "Hello from the other side";
        
        boolean sent = emailService.sendMail(to, subject, body);
        
        Assertions.assertTrue(sent);
    }
}