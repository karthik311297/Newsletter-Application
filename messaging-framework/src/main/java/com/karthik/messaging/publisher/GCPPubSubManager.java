package com.karthik.messaging.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.grpc.GrpcTransportChannel;
import com.google.api.gax.rpc.FixedTransportChannelProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.karthik.messaging.GCPPubSubConfig;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Configuration
public class GCPPubSubManager
{
    @Autowired
    private GCPPubSubConfig GCPPubSubConfig;
    
    @Bean
    public TransportChannelProvider getChannelProvider()
    {
        String gcpPubSubHost = GCPPubSubConfig.getGcpPubSubHost();
        ManagedChannel channel = ManagedChannelBuilder.forTarget(gcpPubSubHost).usePlaintext().build();
        return FixedTransportChannelProvider.create(GrpcTransportChannel.create(channel));
    }
    
    @Bean
    public CredentialsProvider getCredentialProvider()
    {
        // TODO: This will change when having real gcp. There we would need to provide real credentials.
        return NoCredentialsProvider.create();
    }
}
