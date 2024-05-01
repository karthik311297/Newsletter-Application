package com.karthik.newsletterapp.controller;

public class NewsletterSubscriptionRequest
{
    private Long newsletterID;
    private Long userID;
    
    public NewsletterSubscriptionRequest()
    {
    }
    
    public NewsletterSubscriptionRequest(Long newsletterID, Long userID)
    {
        this.newsletterID = newsletterID;
        this.userID = userID;
    }
    
    public Long getNewsletterID()
    {
        return newsletterID;
    }
    
    public void setNewsletterID(Long newsletterID)
    {
        this.newsletterID = newsletterID;
    }
    
    public Long getUserID()
    {
        return userID;
    }
    
    public void setUserID(Long userID)
    {
        this.userID = userID;
    }
}
