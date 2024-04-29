package com.karthik.emailservice;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.karthik.messaging.EmailMessageBody;
import com.karthik.messaging.Message;
import com.karthik.messaging.publisher.GCPPubSubPublisher;

@SpringBootTest
@ContextConfiguration(classes = EmailServiceApplication.class)
class EmailTopicSubscriptionWorkerIT
{
    private static final String EMAIL_TOPIC = "EMAIL-TOPIC";
    
    @Autowired
    GCPPubSubPublisher gcpPubSubPublisher;
    
    @Test
    public void test() throws JsonProcessingException, InterruptedException
    {
        List<String> to = Arrays.asList("karthik.iyer97@gmail.com");
        String subject = "My First Email using google smtp";
        String body = "Hello from the other side";
        EmailMessageBody emailMessageBody = new EmailMessageBody();
        emailMessageBody.setTo(to);
        emailMessageBody.setBody(body);
        emailMessageBody.setSubject(subject);
        Message message = new Message();
        message.setBody(new ObjectMapper().writeValueAsString(emailMessageBody));
        gcpPubSubPublisher.publish(EMAIL_TOPIC, message);
        gcpPubSubPublisher.publish(EMAIL_TOPIC, message);
        Thread.sleep(10000);
    }
}