package com.karthik.newsletterapp.controller;

import java.util.List;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.karthik.newsletterapp.controller.requests.NewsletterArticlePublishRequest;
import com.karthik.newsletterapp.controller.requests.NewsletterRequest;
import com.karthik.newsletterapp.controller.requests.NewsletterSubscriptionRequest;
import com.karthik.newsletterapp.model.Article;
import com.karthik.newsletterapp.model.ArticleNewsletter;
import com.karthik.newsletterapp.model.Newsletter;
import com.karthik.newsletterapp.model.Subscription;
import com.karthik.newsletterapp.service.NewsletterService;

@RestController
@RequestMapping("/api/newsletters")
public class NewsletterController
{
    @Autowired
    private NewsletterService newsletterService;
    
    @PostMapping
    public ResponseEntity<String> createNewsletter(@RequestBody NewsletterRequest newsletterRequest)
    {
        Newsletter newsletter = new Newsletter();
        newsletter.setName(newsletterRequest.getName());
        Newsletter saved = newsletterService.createNewsletter(newsletter, newsletterRequest.getUserID());
        return Objects.isNull(saved) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid User")
                : ResponseEntity.ok("Newsletter saved with id : " + saved.getId());
    }
    
    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeToNewsletter(@RequestBody NewsletterSubscriptionRequest newsletterSubscriptionRequest)
    {
        Subscription saved = newsletterService.subscribeToNewsletter(newsletterSubscriptionRequest.getNewsletterID(), newsletterSubscriptionRequest.getUserID());
        return Objects.isNull(saved) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Newsletter does not exist")
                : ResponseEntity.ok("Successfully subscribed to the newsletter!");
    }
    
    @PostMapping("/publish")
    public ResponseEntity<String> publishArticleToNewsletter(@RequestBody NewsletterArticlePublishRequest newsletterArticlePublishRequest)
    {
        try
        {
            ArticleNewsletter saved = newsletterService.publishArticleInNewsletter(newsletterArticlePublishRequest.getNewsletterID(), newsletterArticlePublishRequest.getArticleID());
            return Objects.isNull(saved) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Newsletter does not exist")
                    : ResponseEntity.ok("Successfully published article to the newsletter!");
        }
        catch(JsonProcessingException | InterruptedException e)
        {
            return ResponseEntity.internalServerError().body("Failed to subscribe to newsletter!");
        }
    }
    
    @GetMapping("/{newsletterID}")
    public ResponseEntity<List<Article>> getArticlesInNewsletter(@PathVariable Long newsletterID)
    {
        List<Article> articles = newsletterService.getArticlesInNewsletter(newsletterID);
        return ResponseEntity.ok(articles);
    }
    
}
