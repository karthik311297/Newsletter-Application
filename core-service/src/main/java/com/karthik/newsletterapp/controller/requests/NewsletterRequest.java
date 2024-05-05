package com.karthik.newsletterapp.controller.requests;

public class NewsletterRequest
{
    private String name;
    
    private Long userID;
    
    public NewsletterRequest()
    {
    }
    
    public NewsletterRequest(String name, Long userID)
    {
        this.name = name;
        this.userID = userID;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
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
