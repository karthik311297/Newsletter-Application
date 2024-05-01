package com.karthik.newsletterapp.controller;

public class ArticleRequest
{
    private String content;
    
    private String title;
    
    private Long userID;
    
    public ArticleRequest()
    {
    }
    
    public ArticleRequest(String content, String title, Long userID)
    {
        this.content = content;
        this.title = title;
        this.userID = userID;
    }
    
    public String getContent()
    {
        return content;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
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
