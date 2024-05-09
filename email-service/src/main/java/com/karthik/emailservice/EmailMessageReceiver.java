package com.karthik.emailservice;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.pubsub.v1.PubsubMessage;
import com.karthik.messaging.EmailMessageBody;
import com.karthik.messaging.subscriber.AbstractMessageReceiver;

@Component
public class EmailMessageReceiver extends AbstractMessageReceiver
{
    @Autowired
    private EmailService emailService;
    private final Logger logger = LoggerFactory.getLogger(EmailMessageReceiver.class);
    
    @Override
    protected void consumeMessage(PubsubMessage pubsubMessage)
    {
        try
        {
            String messageData = pubsubMessage.getData().toStringUtf8();
            EmailMessageBody emailMessageBody = new ObjectMapper()
                    .readValue(messageData, EmailMessageBody.class);
            emailService.sendMail(emailMessageBody.getTo(), emailMessageBody.getSubject(), emailMessageBody.getBody());
            logger.info(
                    MessageFormat.format("consumed message with ID : {0}", pubsubMessage.getMessageId()));
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
        }
    }
}
