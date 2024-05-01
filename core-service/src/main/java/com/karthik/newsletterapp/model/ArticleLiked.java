package com.karthik.newsletterapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import com.karthik.newsletterapp.model.identifier.ArticleLikedID;

public class ArticleLiked
{
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Article article;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private UserDetail userDetail;
    
    public Article getArticle()
    {
        return article;
    }
    
    public void setArticle(Article article)
    {
        this.article = article;
    }
    
    public UserDetail getUserDetail()
    {
        return userDetail;
    }
    
    public void setUserDetail(UserDetail userDetail)
    {
        this.userDetail = userDetail;
    }
}
