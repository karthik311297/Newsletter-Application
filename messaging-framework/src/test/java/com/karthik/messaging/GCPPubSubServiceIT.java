package com.karthik.messaging;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = MessagingFrameworkApplication.class)
class GCPPubSubServiceIT
{
    public static final String TOPIC_1 = "topic1";
    @Autowired
    MessageQueueService messageQueueService;
    
    @Test
    public void shouldCreateTopicIfNotExists()
    {
        messageQueueService.createTopic(TOPIC_1);
        
        List<String> createdTopics = messageQueueService.listTopics();
        Assertions.assertEquals("projects/test-project/topics/%s".formatted(TOPIC_1), createdTopics.get(0));
    }
    
    @Test
    public void shouldNotCreateTopicIfAlreadyExists()
    {
        messageQueueService.createTopic(TOPIC_1);
        
        messageQueueService.createTopic(TOPIC_1);
        List<String> createdTopics = messageQueueService.listTopics();
        
        Assertions.assertEquals(1, createdTopics.size());
        Assertions.assertEquals("projects/test-project/topics/%s".formatted(TOPIC_1), createdTopics.get(0));
    }
    
    @Test
    public void shouldDeleteTopicIfExists()
    {
        messageQueueService.createTopic(TOPIC_1);
        
        messageQueueService.deleteTopic(TOPIC_1);
        List<String> createdTopics = messageQueueService.listTopics();
        
        Assertions.assertEquals(0, createdTopics.size());
    }
    
    @Test
    public void shouldNotDeleteTopicIfItDoesNotExists()
    {
        int beforeDelete = messageQueueService.listTopics().size();
        messageQueueService.deleteTopic(TOPIC_1);
        int afterDelete = messageQueueService.listTopics().size();
        
        Assertions.assertEquals(beforeDelete, afterDelete);
    }
    
    @Test
    public void shouldCreateSubscriptionForTopicIfNotExists()
    {
        messageQueueService.createTopic(TOPIC_1);
        messageQueueService.createSubscriptionForTopic(TOPIC_1);
        
        List<String> createdSubscriptions = messageQueueService.listSubscriptions();
        Assertions.assertEquals("projects/test-project/subscriptions/%s".formatted(TOPIC_1), createdSubscriptions.get(0));
    }
    
    @Test
    public void shouldNotCreateSubscriptionForTopicIfAlreadyExists()
    {
        messageQueueService.createTopic(TOPIC_1);
        messageQueueService.createSubscriptionForTopic(TOPIC_1);
        
        messageQueueService.createSubscriptionForTopic(TOPIC_1);
        List<String> createdSubscriptions = messageQueueService.listSubscriptions();
        
        Assertions.assertEquals(1, createdSubscriptions.size());
        Assertions.assertEquals("projects/test-project/subscriptions/%s".formatted(TOPIC_1), createdSubscriptions.get(0));
    }
    
    @Test
    public void shouldDeleteSubscriptionForTopicIfExists()
    {
        messageQueueService.createTopic(TOPIC_1);
        messageQueueService.createSubscriptionForTopic(TOPIC_1);
        
        messageQueueService.deleteSubscriptionForTopic(TOPIC_1);
        List<String> createdSubscriptions = messageQueueService.listSubscriptions();
        
        Assertions.assertEquals(0, createdSubscriptions.size());
    }
    
    @Test
    public void shouldNotDeleteSubscriptionForTopicIfItDoesNotExists()
    {
        messageQueueService.createTopic(TOPIC_1);
        int beforeDelete = messageQueueService.listSubscriptions().size();
        messageQueueService.deleteSubscriptionForTopic(TOPIC_1);
        int afterDelete = messageQueueService.listSubscriptions().size();
        
        Assertions.assertEquals(beforeDelete, afterDelete);
    }
    
    @AfterEach
    public void cleanup()
    {
        messageQueueService.deleteTopic(TOPIC_1);
        messageQueueService.deleteSubscriptionForTopic(TOPIC_1);
    }
}