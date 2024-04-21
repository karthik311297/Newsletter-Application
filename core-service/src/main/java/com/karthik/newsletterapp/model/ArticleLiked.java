package com.karthik.newsletterapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import com.karthik.newsletterapp.model.identifier.ArticleLikedID;

@Entity
@Table(name = "article_liked")
@IdClass(ArticleLikedID.class)
public class ArticleLiked
{
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Article article;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private User user;
    
    public Article getArticle()
    {
        return article;
    }
    
    public void setArticle(Article article)
    {
        this.article = article;
    }
    
    public User getUser()
    {
        return user;
    }
    
    public void setUser(User user)
    {
        this.user = user;
    }
}
