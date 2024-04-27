package com.karthik.emailservice;

import java.util.List;

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
    
    @Override
    public boolean sendMail(List<String> to, String subject, String body)
    {
        try
        {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(to.toArray(new String[0]));
            mailMessage.setSubject(subject);
            mailMessage.setText(body);
            mailSender.send(mailMessage);
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
