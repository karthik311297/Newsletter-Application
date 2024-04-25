package com.karthik.messaging.subscriber;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;

public abstract class AbstractMessageReceiver implements MessageReceiver
{
    @Override
    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer)
    {
        consumeMessage(pubsubMessage);
        ackReplyConsumer.ack();
    }
    
    protected abstract void consumeMessage(PubsubMessage pubsubMessage);
}
