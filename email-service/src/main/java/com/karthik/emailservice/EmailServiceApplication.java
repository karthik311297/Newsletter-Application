package com.karthik.emailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("com.karthik.messaging")
@ComponentScan("com.karthik.emailservice")
//@EnableConfigurationProperties({GCPPubSubConfig.class})
public class EmailServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(EmailServiceApplication.class, args);
    }
}
