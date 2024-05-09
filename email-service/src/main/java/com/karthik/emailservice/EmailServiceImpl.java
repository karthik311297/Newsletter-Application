package com.karthik.emailservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService
{
    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${spring.mail.username}")
    private String sender;
    
    private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    
    @Override
    public boolean sendMail(List<String> to, String subject, String body)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(to.toArray(new String[0]));
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailSender.send(mailMessage);
        logger.info("Sent email to newsletter subscribers!");
        return true;
    }
}
