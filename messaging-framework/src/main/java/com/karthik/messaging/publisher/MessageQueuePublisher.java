package com.karthik.messaging.publisher;

import com.karthik.messaging.Message;

public interface MessageQueuePublisher
{
    boolean publish(String topic, Message message) throws InterruptedException;
}
