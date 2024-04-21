package com.karthik.newsletterapp.model.identifier;

import java.io.Serializable;
import java.util.Objects;

public class ArticleLikedID implements Serializable
{
    private Long article;
    private Long user;
    
    public ArticleLikedID()
    {
    }
    
    public ArticleLikedID(Long article, Long user)
    {
        this.article = article;
        this.user = user;
    }
    
    public Long getArticle()
    {
        return article;
    }
    
    public void setArticle(Long article)
    {
        this.article = article;
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
        if(!(o instanceof ArticleLikedID that)) return false;
        return getArticle().equals(that.getArticle()) && getUser().equals(that.getUser());
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(getArticle(), getUser());
    }
}
