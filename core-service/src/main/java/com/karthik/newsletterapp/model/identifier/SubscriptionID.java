package com.karthik.newsletterapp.model.identifier;

import java.io.Serializable;
import java.util.Objects;

public class SubscriptionID implements Serializable
{
    private Long newsletter;
    private Long userDetail;
    
    public SubscriptionID()
    {
    }
    
    public SubscriptionID(Long newsletter, Long userDetail)
    {
        this.newsletter = newsletter;
        this.userDetail = userDetail;
    }
    
    public Long getNewsletter()
    {
        return newsletter;
    }
    
    public void setNewsletter(Long newsletter)
    {
        this.newsletter = newsletter;
    }
    
    public Long getUserDetail()
    {
        return userDetail;
    }
    
    public void setUserDetail(Long userDetail)
    {
        this.userDetail = userDetail;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(!(o instanceof SubscriptionID that)) return false;
        return getNewsletter().equals(that.getNewsletter()) && getUserDetail().equals(that.getUserDetail());
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(getNewsletter(), getUserDetail());
    }
}
