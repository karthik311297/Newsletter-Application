package com.karthik.newsletterapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import com.karthik.newsletterapp.model.identifier.ArticleNewsletterIdentifier;

@Entity
@Table(name = "article_newsletter")
@IdClass(ArticleNewsletterIdentifier.class)
public class ArticleNewsletter
{
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "newsletter", referencedColumnName = "id")
    private Newsletter newsletter;
    
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "article", referencedColumnName = "id")
    private Article article;
    
    public Newsletter getNewsletter()
    {
        return newsletter;
    }
    
    public void setNewsletter(Newsletter newsletter)
    {
        this.newsletter = newsletter;
    }
    
    public Article getArticle()
    {
        return article;
    }
    
    public void setArticle(Article article)
    {
        this.article = article;
    }
}
