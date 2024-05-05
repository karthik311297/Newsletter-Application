package com.karthik.newsletterapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    public long getArticleLikeCount(Long articleID)
    {
        Optional<Article> optionalArticle = articleRepository
                .findById(articleID);
        return optionalArticle
                .map(article -> articleLikedRepository.countByArticle(article))
                .orElse(0L);
    }
    
    public void likeArticle(Long articleID, Long userID)
    {
        Optional<Article> optionalArticle = articleRepository
                .findById(articleID);
        Optional<UserDetail> optionalUser = userRepository
                .findById(userID);
        if(optionalArticle.isPresent() && optionalUser.isPresent())
        {
            ArticleLiked articleLiked = new ArticleLiked();
            articleLiked.setArticle(optionalArticle.get());
            articleLiked.setUserDetail(optionalUser.get());
            articleLikedRepository.save(articleLiked);
        }
    }
}
