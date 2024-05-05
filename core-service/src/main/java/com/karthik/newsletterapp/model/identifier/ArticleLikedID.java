package com.karthik.newsletterapp.model.identifier;

import java.io.Serializable;
import java.util.Objects;

public class ArticleLikedID implements Serializable
{
    private Long article;
    private Long userDetail;
    
    public ArticleLikedID()
    {
    }
    
    public ArticleLikedID(Long article, Long userDetail)
    {
        this.article = article;
        this.userDetail = userDetail;
    }
    
    public Long getArticle()
    {
        return article;
    }
    
    public void setArticle(Long article)
    {
        this.article = article;
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
        if(!(o instanceof ArticleLikedID that)) return false;
        return getArticle().equals(that.getArticle()) && getUserDetail().equals(that.getUserDetail());
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(getArticle(), getUserDetail());
    }
}
