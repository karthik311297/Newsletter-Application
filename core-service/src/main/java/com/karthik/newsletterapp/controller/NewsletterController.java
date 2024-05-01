package com.karthik.newsletterapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.karthik.newsletterapp.model.Article;
import com.karthik.newsletterapp.model.Newsletter;
import com.karthik.newsletterapp.model.Subscription;
import com.karthik.newsletterapp.service.ArticleService;
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
        return ResponseEntity.ok("Newsletter saved with id : " + saved.getId());
    }
    
    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeToNewsletter(@RequestBody NewsletterSubscriptionRequest newsletterSubscriptionRequest)
    {
        newsletterService.subscribeToNewsletter(newsletterSubscriptionRequest.getNewsletterID(), newsletterSubscriptionRequest.getUserID());
        return ResponseEntity.ok("Successfully subscribed to the newsletter!");
    }
    
    @PostMapping("/publish")
    public ResponseEntity<String> publishArticleToNewsletter(@RequestBody NewsletterArticlePublishRequest newsletterArticlePublishRequest)
    {
        try
        {
            newsletterService.publishArticleInNewsletter(newsletterArticlePublishRequest.getNewsletterID(), newsletterArticlePublishRequest.getArticleID());
            return ResponseEntity.ok("Successfully subscribed to the newsletter!");
        }
        catch(JsonProcessingException | InterruptedException e)
        {
            return ResponseEntity.internalServerError().body("Failed to subscribe to newsletter!");
        }
    }
}
