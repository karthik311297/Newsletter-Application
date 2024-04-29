package com.karthik.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.pubsub.v1.PubsubMessage;
import com.karthik.messaging.EmailMessageBody;
import com.karthik.messaging.subscriber.AbstractMessageReceiver;

@Component
public class EmailMessageReceiver extends AbstractMessageReceiver
{
    @Autowired
    private EmailService emailService;
    
    @Override
    protected void consumeMessage(PubsubMessage pubsubMessage)
    {
        try
        {
            String messageData = pubsubMessage.getData().toStringUtf8();
            EmailMessageBody emailMessageBody = new ObjectMapper()
                    .readValue(messageData, EmailMessageBody.class);
            emailService.sendMail(emailMessageBody.getTo(), emailMessageBody.getSubject(), emailMessageBody.getBody());
        }
        catch(JsonProcessingException e)
        {
            System.out.println(e);
        }
    }
}
