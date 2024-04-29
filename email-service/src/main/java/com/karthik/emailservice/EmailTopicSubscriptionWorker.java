package com.karthik.emailservice;

import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.karthik.messaging.GCPPubSubConfig;
import com.karthik.messaging.MessageQueueService;
import com.karthik.messaging.publisher.GCPPubSubManager;

@Component
public class EmailTopicSubscriptionWorker implements ApplicationListener<ApplicationReadyEvent>
{
    private static final String EMAIL_TOPIC = "EMAIL-TOPIC";

    @Autowired
    private GCPPubSubManager gcpPubSubManager;
    
    @Autowired
    private GCPPubSubConfig gcpPubSubConfig;
    
    @Autowired
    private MessageQueueService messageQueueService;
    
    @Autowired
    private EmailMessageReceiver emailMessageReceiver;
    
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event)
    {
        messageQueueService.createTopic(EMAIL_TOPIC);
        messageQueueService.createSubscriptionForTopic(EMAIL_TOPIC);
        Executors.newSingleThreadExecutor().submit(new Runnable()
        {
            @Override
            public void run()
            {
                runSubscription();
            }
        });
    }
    
    private void runSubscription()
    {
        Subscriber subscriber = null;
        ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(gcpPubSubConfig.getGcpProjectID(), EMAIL_TOPIC);
        
        try
        {
            subscriber = Subscriber.newBuilder(subscriptionName, emailMessageReceiver)
                    .setChannelProvider(gcpPubSubManager.getChannelProvider())
                    .setCredentialsProvider(gcpPubSubManager.getCredentialProvider()).build();
            // Start the subscriber.
            subscriber.startAsync().awaitRunning();
            System.out.printf("Listening for messages on %s:\n", subscriptionName.toString());
            subscriber.awaitTerminated();
        }
        catch(Exception e)
        {
            subscriber.stopAsync().awaitTerminated();
        }
        finally
        {
            subscriber.stopAsync().awaitTerminated();
        }
    }
}
