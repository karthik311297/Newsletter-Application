package com.karthik.newsletterapp.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthik.newsletterapp.controller.requests.ArticleLikeRequest;
import com.karthik.newsletterapp.controller.requests.ArticleRequest;
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
        return Objects.isNull(saved) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid User")
                : ResponseEntity.ok("Article saved with id : " + saved.getId());
    }
    
    @PostMapping("/like")
    public ResponseEntity<String> likeArticle(@RequestBody ArticleLikeRequest articleLikeRequest)
    {
        int liked = articleService.likeArticle(articleLikeRequest.getArticleID(), articleLikeRequest.getUserID());
        return liked == 0 ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article does not exist!")
                : ResponseEntity.ok("Article Liked!");
    }
    
    @GetMapping("/countlikes/{articleID}")
    public ResponseEntity<Long> getArticleLikeCount(@PathVariable Long articleID)
    {
        long count = articleService.getArticleLikeCount(articleID);
        return ResponseEntity.ok(count);
    }
}
