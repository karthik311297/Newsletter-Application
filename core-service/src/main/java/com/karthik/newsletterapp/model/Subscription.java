package com.karthik.newsletterapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import com.karthik.newsletterapp.model.identifier.SubscriptionID;

@Entity
@Table(name = "subscription")
@IdClass(SubscriptionID.class)
public class Subscription
{
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Newsletter newsletter;
    
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private User user;
    
    public Newsletter getNewsletter()
    {
        return newsletter;
    }
    
    public void setNewsletter(Newsletter newsletter)
    {
        this.newsletter = newsletter;
    }
    
    public User getUser()
    {
        return user;
    }
    
    public void setUser(User user)
    {
        this.user = user;
    }
}
