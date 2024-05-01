package com.karthik.newsletterapp.controller;

public class NewsletterArticlePublishRequest
{
    private Long newsletterID;
    private Long articleID;
    
    public NewsletterArticlePublishRequest()
    {
    }
    
    public NewsletterArticlePublishRequest(Long newsletterID, Long articleID)
    {
        this.newsletterID = newsletterID;
        this.articleID = articleID;
    }
    
    public Long getNewsletterID()
    {
        return newsletterID;
    }
    
    public void setNewsletterID(Long newsletterID)
    {
        this.newsletterID = newsletterID;
    }
    
    public Long getArticleID()
    {
        return articleID;
    }
    
    public void setArticleID(Long articleID)
    {
        this.articleID = articleID;
    }
}
