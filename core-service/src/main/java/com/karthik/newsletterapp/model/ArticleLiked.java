package com.karthik.newsletterapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import com.karthik.newsletterapp.model.identifier.ArticleLikedID;
import com.karthik.newsletterapp.model.identifier.SubscriptionID;

@Entity
@Table(name = "article_liked")
@IdClass(ArticleLikedID.class)
public class ArticleLiked
{
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "article", referencedColumnName = "id")
    private Article article;
    
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userDetail", referencedColumnName = "id")
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
