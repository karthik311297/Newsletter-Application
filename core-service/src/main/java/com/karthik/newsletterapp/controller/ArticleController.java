package com.karthik.newsletterapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthik.newsletterapp.model.Article;
import com.karthik.newsletterapp.service.ArticleService;

@RestController
@RequestMapping("/api/articles")
public class ArticleController
{
    @Autowired
    private ArticleService articleService;
    
    @PostMapping
    public ResponseEntity<String> saveArticle(@RequestBody ArticleRequest articleRequest)
    {
        Article article = new Article();
        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        Article saved = articleService.createArticle(article, articleRequest.getUserID());
        return ResponseEntity.ok("Article saved with id : " + saved.getId());
    }
}
