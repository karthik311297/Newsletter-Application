package com.karthik.messaging;

import java.util.List;

public interface MessageQueueService
{
    boolean createTopic(String topic);
    
    boolean deleteTopic(String topic);
    
    List<String> listTopics();
    
    boolean createSubscriptionForTopic(String topic);
    
    boolean deleteSubscriptionForTopic(String topic);
    
    List<String> listSubscriptions();
}
