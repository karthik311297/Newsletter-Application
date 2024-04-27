package com.karthik.messaging;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.NoCredentialsProvider;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import com.karthik.messaging.publisher.GCPPubSubManager;
import com.karthik.messaging.publisher.GCPPubSubPublisher;
import com.karthik.messaging.subscriber.AbstractMessageReceiver;

@SpringBootTest
@ContextConfiguration(classes = MessagingFrameworkApplication.class)
public class GCPPubSubPublisherIT
{
    private static final String TOPIC_1 = "topic1";
    
    @Autowired
    private GCPPubSubConfig GCPPubSubConfig;
    
    @Autowired
    MessageQueueService messageQueueService;
    
    @Autowired
    GCPPubSubPublisher gcpPubSubPublisher;
    
    @Autowired
    private GCPPubSubManager gcpPubSubManager;
    
    @AfterEach
    public void cleanup()
    {
        messageQueueService.deleteTopic(TOPIC_1);
        messageQueueService.deleteSubscriptionForTopic(TOPIC_1);
    }
    
    @Test
    public void shouldPublishMessageToTopicAndBeAbleToSubscribeToTheTopicAndConsumeTheMessage()
    {
        messageQueueService.createTopic(TOPIC_1);
        messageQueueService.createSubscriptionForTopic(TOPIC_1);
        MessageReceiver testReceiver = new AbstractMessageReceiver()
        {
            @Override
            protected void consumeMessage(PubsubMessage pubsubMessage)
            {
                System.out.println("Id: " + pubsubMessage.getMessageId());
                System.out.println("Data: " + pubsubMessage.getData().toStringUtf8());
            }
        };
        ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(GCPPubSubConfig.getGcpProjectID(), TOPIC_1);
        
        CredentialsProvider credentialsProvider = NoCredentialsProvider.create();
        Subscriber subscriber = null;
        try
        {
            subscriber = Subscriber.newBuilder(subscriptionName, testReceiver)
                    .setChannelProvider(gcpPubSubManager.getChannelProvider())
                    .setCredentialsProvider(credentialsProvider).build();
            // Start the subscriber.
            subscriber.startAsync().awaitRunning();
            System.out.printf("Listening for messages on %s:\n", subscriptionName.toString());
            Message m = new Message();
            m.setBody("Hello First Message");
            gcpPubSubPublisher.publish(TOPIC_1, m);
            // Allow the subscriber to run for 30s unless an unrecoverable error occurs.
            subscriber.awaitTerminated(5, TimeUnit.SECONDS);
        }
        catch(TimeoutException timeoutException)
        {
            System.out.println(timeoutException);
            // Shut down the subscriber after 30s. Stop receiving messages.
            subscriber.stopAsync();
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
            subscriber.stopAsync();
        }
    }
}
