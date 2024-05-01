package com.karthik.newsletterapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthik.newsletterapp.model.Article;
import com.karthik.newsletterapp.model.UserDetail;
import com.karthik.newsletterapp.repository.ArticleRepository;
import com.karthik.newsletterapp.repository.UserRepository;

@Service
public class ArticleService
{
    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public Article createArticle(Article article, Long userID)
    {
        Optional<UserDetail> optionalUser = userRepository
                .findById(userID);
        if(optionalUser.isPresent())
        {
            article.setUserDetail(optionalUser.get());
            return articleRepository.save(article);
        }
        return null;
    }
}
