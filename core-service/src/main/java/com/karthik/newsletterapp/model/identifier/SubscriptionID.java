package com.karthik.newsletterapp.model.identifier;

import java.io.Serializable;
import java.util.Objects;

public class SubscriptionID implements Serializable
{
    private Long newsletter;
    private Long user;
    
    public SubscriptionID()
    {
    }
    
    public SubscriptionID(Long newsletter, Long user)
    {
        this.newsletter = newsletter;
        this.user = user;
    }
    
    public Long getNewsletter()
    {
        return newsletter;
    }
    
    public void setNewsletter(Long newsletter)
    {
        this.newsletter = newsletter;
    }
    
    public Long getUser()
    {
        return user;
    }
    
    public void setUser(Long user)
    {
        this.user = user;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(!(o instanceof SubscriptionID that)) return false;
        return getNewsletter().equals(that.getNewsletter()) && getUser().equals(that.getUser());
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(getNewsletter(), getUser());
    }
}
