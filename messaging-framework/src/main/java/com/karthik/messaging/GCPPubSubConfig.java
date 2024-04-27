package com.karthik.messaging;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.newsletter")
public class GCPPubSubConfig
{
    private String gcpProjectID;
    private String gcpPubSubHost;
    
    public String getGcpProjectID()
    {
        return gcpProjectID;
    }
    
    public void setGcpProjectID(String gcpProjectID)
    {
        this.gcpProjectID = gcpProjectID;
    }
    
    public String getGcpPubSubHost()
    {
        return gcpPubSubHost;
    }
    
    public void setGcpPubSubHost(String gcpPubSubHost)
    {
        this.gcpPubSubHost = gcpPubSubHost;
    }
}
