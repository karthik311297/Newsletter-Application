package com.karthik.newsletterapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthik.newsletterapp.exception.ArticleNotFoundException;
import com.karthik.newsletterapp.exception.UserNotFoundException;
import com.karthik.newsletterapp.model.Article;
import com.karthik.newsletterapp.model.ArticleLiked;
import com.karthik.newsletterapp.model.UserDetail;
import com.karthik.newsletterapp.repository.ArticleLikedRepository;
import com.karthik.newsletterapp.repository.ArticleRepository;
import com.karthik.newsletterapp.repository.UserRepository;

@Service
public class ArticleService
{
    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private ArticleLikedRepository articleLikedRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public Article createArticle(Article article, Long userID) throws UserNotFoundException
    {
        Optional<UserDetail> optionalUser = userRepository
                .findById(userID);
        if(optionalUser.isEmpty()) throw new UserNotFoundException();
        article.setUserDetail(optionalUser.get());
        return articleRepository.save(article);
    }
    
    public long getArticleLikeCount(Long articleID)
    {
        Optional<Article> optionalArticle = articleRepository
                .findById(articleID);
        return optionalArticle
                .map(article -> articleLikedRepository.countByArticle(article))
                .orElse(0L);
    }
    
    public int likeArticle(Long articleID, Long userID) throws UserNotFoundException, ArticleNotFoundException
    {
        Optional<Article> optionalArticle = articleRepository
                .findById(articleID);
        Optional<UserDetail> optionalUser = userRepository
                .findById(userID);
        if(optionalArticle.isEmpty()) throw new ArticleNotFoundException();
        if(optionalUser.isEmpty()) throw new UserNotFoundException();
        ArticleLiked articleLiked = new ArticleLiked();
        articleLiked.setArticle(optionalArticle.get());
        articleLiked.setUserDetail(optionalUser.get());
        articleLikedRepository.save(articleLiked);
        return 1;
    }
}
