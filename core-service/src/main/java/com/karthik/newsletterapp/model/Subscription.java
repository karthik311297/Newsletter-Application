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
    @JoinColumn(name = "newsletter", referencedColumnName = "id")
    private Newsletter newsletter;
    
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userDetail", referencedColumnName = "id")
    private UserDetail userDetail;
    
    public Newsletter getNewsletter()
    {
        return newsletter;
    }
    
    public void setNewsletter(Newsletter newsletter)
    {
        this.newsletter = newsletter;
    }
    
    public UserDetail getUserDetail()
    {
        return userDetail;
    }
    
    public void setUserDetail(UserDetail userDetail)
    {
        this.userDetail = userDetail;
    }
}
