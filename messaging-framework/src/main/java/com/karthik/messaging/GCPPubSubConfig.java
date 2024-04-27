package com.karthik.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GCPPubSubConfig
{
    @Value("${app.newsletter.gcpProjectID}")
    private String gcpProjectID;
    @Value("${app.newsletter.gcpPubSubHost}")
    private String gcpPubSubHost;
    
    @Bean
    public String getGcpProjectID()
    {
        return gcpProjectID;
    }
    
    @Bean
    public String getGcpPubSubHost()
    {
        return gcpPubSubHost;
    }
    
}
