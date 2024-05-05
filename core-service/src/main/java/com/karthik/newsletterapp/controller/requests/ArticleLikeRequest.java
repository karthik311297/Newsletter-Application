package com.karthik.newsletterapp.controller.requests;

public class ArticleLikeRequest
{
    private Long articleID;
    private Long userID;
    
    public ArticleLikeRequest()
    {
    }
    
    public ArticleLikeRequest(Long articleID, Long userID)
    {
        this.articleID = articleID;
        this.userID = userID;
    }
    
    public Long getArticleID()
    {
        return articleID;
    }
    
    public void setArticleID(Long articleID)
    {
        this.articleID = articleID;
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
