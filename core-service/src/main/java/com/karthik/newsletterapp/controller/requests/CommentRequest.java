package com.karthik.newsletterapp.controller.requests;

public class CommentRequest
{
    private Long articleID;
    private String commentContent;
    
    public CommentRequest()
    {
    }
    
    public CommentRequest(Long articleID, String commentContent)
    {
        this.articleID = articleID;
        this.commentContent = commentContent;
    }
    
    public Long getArticleID()
    {
        return articleID;
    }
    
    public void setArticleID(Long articleID)
    {
        this.articleID = articleID;
    }
    
    public String getCommentContent()
    {
        return commentContent;
    }
    
    public void setCommentContent(String commentContent)
    {
        this.commentContent = commentContent;
    }
}
