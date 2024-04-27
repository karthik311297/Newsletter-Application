package com.karthik.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.karthik.messaging")
public class MessagingFrameworkApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(MessagingFrameworkApplication.class, args);
    }
}