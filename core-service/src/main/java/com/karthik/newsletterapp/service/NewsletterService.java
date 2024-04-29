package com.karthik.newsletterapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthik.newsletterapp.model.Article;
import com.karthik.newsletterapp.model.ArticleNewsletter;
import com.karthik.newsletterapp.model.Newsletter;
import com.karthik.newsletterapp.model.Subscription;
import com.karthik.newsletterapp.model.User;
import com.karthik.newsletterapp.repository.NewsletterRepository;
import com.karthik.newsletterapp.repository.UserRepository;

@Service
public class NewsletterService
{
    @Autowired
    private NewsletterRepository newsletterRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public Newsletter createNewsletter(Newsletter newsletter, Long userID)
    {
        newsletter.setUser(userRepository
                .findById(userID).get());
        return newsletterRepository.save(newsletter);
    }
    
    public ArticleNewsletter publishArticleInNewsletter(Long newsletterID, Article article)
    {
        return null;
    }
    
    public Subscription subscribeToNewsletter(Long newsletterID, Long userID)
    {
        return null;
    }
    
    private List<User> getNewsletterSubscribers(Long newsletterID)
    {
        return null;
    }
    
    private List<Article> getArticlesInNewsletter(Long newsletterID)
    {
        return null;
    }
}
