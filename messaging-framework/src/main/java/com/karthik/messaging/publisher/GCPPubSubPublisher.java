package com.karthik.messaging.publisher;

import java.text.MessageFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.core.CredentialsProvider;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import com.karthik.messaging.GCPPubSubConfig;
import com.karthik.messaging.Message;

@Component
public class GCPPubSubPublisher implements MessageQueuePublisher
{
    
    @Autowired
    private GCPPubSubConfig GCPPubSubConfig;
    
    @Autowired
    private GCPPubSubManager gcpPubSubManager;
    
    private static final Logger logger = LoggerFactory.getLogger(GCPPubSubPublisher.class);
    
    @Override
    public boolean publish(String topic, Message message) throws InterruptedException
    {
        String gcpProjectID = GCPPubSubConfig.getGcpProjectID();
        ProjectTopicName topicName = ProjectTopicName.of(gcpProjectID, topic);
        CredentialsProvider credentialsProvider = gcpPubSubManager.getCredentialProvider();
        Publisher publisher = null;
        try
        {
            publisher = Publisher.newBuilder(topicName)
                    .setChannelProvider(gcpPubSubManager.getChannelProvider())
                    .setCredentialsProvider(credentialsProvider).build();
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(ByteString.copyFromUtf8(message.getBody())).build();
            ApiFuture<String> future = publisher.publish(pubsubMessage);
            ApiFutures.addCallback(future, new ApiFutureCallback<>()
            {
                @Override
                public void onFailure(Throwable throwable)
                {
                    logger.error(MessageFormat.format("Error while publishing message: {0}", throwable.getMessage()));
                }
                
                @Override
                public void onSuccess(String s)
                {
                    logger.info(MessageFormat.format("Published message ID :{0}", s));
                }
            }, Executors.newSingleThreadExecutor());
            
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
            return false;
        }
        finally
        {
            publisher.shutdown();
            publisher.awaitTermination(30, TimeUnit.SECONDS);
        }
        return true;
    }
}
