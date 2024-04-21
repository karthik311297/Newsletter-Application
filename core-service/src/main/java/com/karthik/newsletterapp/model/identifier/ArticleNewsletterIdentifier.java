package com.karthik.newsletterapp.model.identifier;

import java.io.Serializable;
import java.util.Objects;

public class ArticleNewsletterIdentifier implements Serializable
{
    private Long newsletter;
    private Long article;
    
    public ArticleNewsletterIdentifier()
    {
    }
    
    public ArticleNewsletterIdentifier(Long newsletter, Long article)
    {
        this.newsletter = newsletter;
        this.article = article;
    }
    
    public Long getNewsletter()
    {
        return newsletter;
    }
    
    public void setNewsletter(Long newsletter)
    {
        this.newsletter = newsletter;
    }
    
    public Long getArticle()
    {
        return article;
    }
    
    public void setArticle(Long article)
    {
        this.article = article;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(!(o instanceof ArticleNewsletterIdentifier that)) return false;
        return getNewsletter().equals(that.getNewsletter()) && getArticle().equals(that.getArticle());
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(getNewsletter(), getArticle());
    }
}
