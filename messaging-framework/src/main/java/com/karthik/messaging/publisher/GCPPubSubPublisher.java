package com.karthik.messaging.publisher;

import com.karthik.messaging.Message;

public class GCPPubSubPublisher implements MessageQueuePublisher
{
    @Override
    public boolean publish(String topic, Message message)
    {
        return false;
    }
}
