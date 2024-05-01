package com.karthik.newsletterapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.karthik.newsletterapp")
@ComponentScan("com.karthik.messaging")
public class NewsletterCoreApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(NewsletterCoreApp.class, args);
    }
}