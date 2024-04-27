package com.karthik.messaging;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.rpc.NotFoundException;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.cloud.pubsub.v1.SubscriptionAdminSettings;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.cloud.pubsub.v1.TopicAdminSettings;
import com.google.pubsub.v1.ProjectName;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PushConfig;
import com.google.pubsub.v1.SubscriptionName;
import com.google.pubsub.v1.TopicName;
import com.karthik.messaging.publisher.GCPPubSubManager;

@Component
public class GCPPubSubService implements MessageQueueService
{
    @Autowired
    private GCPPubSubConfig GCPPubSubConfig;
    
    @Autowired
    private GCPPubSubManager gcpPubSubManager;
    
    @Override
    public boolean createTopic(String topic)
    {
        String gcpProjectID = GCPPubSubConfig.getGcpProjectID();
        CredentialsProvider credentialsProvider = gcpPubSubManager.getCredentialProvider();
        try(TopicAdminClient topicAdminClient = TopicAdminClient.create(TopicAdminSettings.newBuilder()
                .setCredentialsProvider(credentialsProvider)
                .setTransportChannelProvider(gcpPubSubManager.getChannelProvider()).build()))
        {
            TopicName topicName = TopicName.of(gcpProjectID, topic);
            if(!doesTopicAlreadyExists(topicAdminClient, topicName))
            {
                topicAdminClient.createTopic(topicName);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean deleteTopic(String topic)
    {
        String gcpProjectID = GCPPubSubConfig.getGcpProjectID();
        CredentialsProvider credentialsProvider = gcpPubSubManager.getCredentialProvider();
        try(TopicAdminClient topicAdminClient = TopicAdminClient.create(TopicAdminSettings.newBuilder()
                .setCredentialsProvider(credentialsProvider)
                .setTransportChannelProvider(gcpPubSubManager.getChannelProvider()).build()))
        {
            TopicName topicName = TopicName.of(gcpProjectID, topic);
            if(doesTopicAlreadyExists(topicAdminClient, topicName))
            {
                topicAdminClient.deleteTopic(topicName);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    @Override
    public List<String> listTopics()
    {
        List<String> topics = new ArrayList<>();
        String gcpProjectID = GCPPubSubConfig.getGcpProjectID();
        CredentialsProvider credentialsProvider = gcpPubSubManager.getCredentialProvider();
        try(TopicAdminClient topicAdminClient = TopicAdminClient.create(TopicAdminSettings.newBuilder()
                .setCredentialsProvider(credentialsProvider)
                .setTransportChannelProvider(gcpPubSubManager.getChannelProvider()).build()))
        {
            TopicAdminClient.ListTopicsPagedResponse response = topicAdminClient
                    .listTopics(ProjectName.of(gcpProjectID));
            response.iterateAll().forEach(topic -> topics.add(topic.getName()));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return topics;
    }
    
    private boolean doesTopicAlreadyExists(TopicAdminClient topicAdminClient, TopicName topicName)
    {
        try
        {
            topicAdminClient.getTopic(topicName);
        }
        catch(NotFoundException e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    
    @Override
    public boolean createSubscriptionForTopic(String topic)
    {
        String gcpProjectID = GCPPubSubConfig.getGcpProjectID();
        ProjectTopicName topicName = ProjectTopicName.of(gcpProjectID, topic);
        SubscriptionName subscriptionName = SubscriptionName.of(gcpProjectID, topic);
        CredentialsProvider credentialsProvider = gcpPubSubManager.getCredentialProvider();
        try(SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create(SubscriptionAdminSettings.newBuilder()
                .setCredentialsProvider(credentialsProvider)
                .setTransportChannelProvider(gcpPubSubManager.getChannelProvider()).build()))
        {
            if(!doesSubscriptionAlreadyExists(subscriptionAdminClient, subscriptionName))
            {
                subscriptionAdminClient.createSubscription(subscriptionName, topicName, PushConfig.getDefaultInstance(), 30);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean deleteSubscriptionForTopic(String topic)
    {
        String gcpProjectID = GCPPubSubConfig.getGcpProjectID();
        SubscriptionName subscriptionName = SubscriptionName.of(gcpProjectID, topic);
        CredentialsProvider credentialsProvider = gcpPubSubManager.getCredentialProvider();
        try(SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create(SubscriptionAdminSettings.newBuilder()
                .setCredentialsProvider(credentialsProvider)
                .setTransportChannelProvider(gcpPubSubManager.getChannelProvider()).build()))
        {
            if(doesSubscriptionAlreadyExists(subscriptionAdminClient, subscriptionName))
            {
                subscriptionAdminClient.deleteSubscription(subscriptionName);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    private boolean doesSubscriptionAlreadyExists(SubscriptionAdminClient subscriptionAdminClient, SubscriptionName subscriptionName)
    {
        try
        {
            subscriptionAdminClient.getSubscription(subscriptionName);
        }
        catch(NotFoundException e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    @Override
    public List<String> listSubscriptions()
    {
        List<String> subscriptions = new ArrayList<>();
        String gcpProjectID = GCPPubSubConfig.getGcpProjectID();
        CredentialsProvider credentialsProvider = gcpPubSubManager.getCredentialProvider();
        try(SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create(SubscriptionAdminSettings.newBuilder()
                .setCredentialsProvider(credentialsProvider)
                .setTransportChannelProvider(gcpPubSubManager.getChannelProvider()).build()))
        {
            SubscriptionAdminClient.ListSubscriptionsPagedResponse response = subscriptionAdminClient
                    .listSubscriptions(ProjectName.of(gcpProjectID));
            response.iterateAll().forEach(subscription -> subscriptions.add(subscription.getName()));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return subscriptions;
    }
}
