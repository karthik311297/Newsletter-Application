package com.karthik.newsletterapp.service;

import static com.karthik.messaging.Topics.EMAIL_TOPIC;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.karthik.messaging.EmailMessageBody;
import com.karthik.messaging.Message;
import com.karthik.messaging.publisher.GCPPubSubPublisher;
import com.karthik.newsletterapp.exception.ArticleNotFoundException;
import com.karthik.newsletterapp.exception.NewsletterNotFoundException;
import com.karthik.newsletterapp.exception.UserNotFoundException;
import com.karthik.newsletterapp.model.Article;
import com.karthik.newsletterapp.model.ArticleNewsletter;
import com.karthik.newsletterapp.model.Newsletter;
import com.karthik.newsletterapp.model.Subscription;
import com.karthik.newsletterapp.model.UserDetail;
import com.karthik.newsletterapp.repository.ArticleNewsletterRepository;
import com.karthik.newsletterapp.repository.ArticleRepository;
import com.karthik.newsletterapp.repository.NewsletterRepository;
import com.karthik.newsletterapp.repository.SubscriptionRepository;
import com.karthik.newsletterapp.repository.UserRepository;

@Service
public class NewsletterService
{
    @Autowired
    private ArticleNewsletterRepository articleNewsletterRepository;
    @Autowired
    private NewsletterRepository newsletterRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    
    @Autowired
    private GCPPubSubPublisher gcpPubSubPublisher;
    
    public Newsletter createNewsletter(Newsletter newsletter, Long userID) throws UserNotFoundException
    {
        Optional<UserDetail> optionalUser = userRepository
                .findById(userID);
        if(optionalUser.isEmpty()) throw new UserNotFoundException();
        newsletter.setUserDetail(optionalUser.get());
        return newsletterRepository.save(newsletter);
    }
    
    public ArticleNewsletter publishArticleInNewsletter(Long newsletterID, Long articleID)
            throws JsonProcessingException, InterruptedException
            , ArticleNotFoundException, NewsletterNotFoundException
    {
        Optional<Newsletter> optionalNewsletter = newsletterRepository.findById(newsletterID);
        Optional<Article> optionalArticle = articleRepository.findById(articleID);
        ArticleNewsletter articleNewsletter = null;
        if(optionalNewsletter.isEmpty()) throw new NewsletterNotFoundException();
        if(optionalArticle.isEmpty()) throw new ArticleNotFoundException();
        List<String> subscriberEmailAddresses = getNewsletterSubscribers(newsletterID)
                .stream()
                .map(UserDetail::getEmailID)
                .collect(Collectors.toList());
        Newsletter newsletter = optionalNewsletter.get();
        Article article = optionalArticle.get();
        articleNewsletter = new ArticleNewsletter();
        articleNewsletter.setArticle(article);
        articleNewsletter.setNewsletter(newsletter);
        articleNewsletterRepository.save(articleNewsletter);
        publishMessage(subscriberEmailAddresses, newsletter, article);
        return articleNewsletter;
    }
    
    private void publishMessage(List<String> subscriberEmailAddresses,
                                Newsletter newsletter, Article article) throws JsonProcessingException, InterruptedException
    {
        EmailMessageBody emailMessageBody = new EmailMessageBody();
        emailMessageBody.setTo(subscriberEmailAddresses);
        emailMessageBody.setSubject("New article published in newsletter : " + newsletter.getName());
        emailMessageBody.setBody(article.getTitle());
        Message message = new Message();
        message.setBody(new ObjectMapper().writeValueAsString(emailMessageBody));
        gcpPubSubPublisher.publish(EMAIL_TOPIC, message);
    }
    
    public Subscription subscribeToNewsletter(Long newsletterID, Long userID)
            throws NewsletterNotFoundException, UserNotFoundException
    {
        Optional<UserDetail> optionalUser = userRepository
                .findById(userID);
        Optional<Newsletter> optionalNewsletter = newsletterRepository.findById(newsletterID);
        if(optionalUser.isEmpty()) throw new UserNotFoundException();
        if(optionalNewsletter.isEmpty()) throw new NewsletterNotFoundException();
        Subscription s = new Subscription();
        s.setNewsletter(optionalNewsletter.get());
        s.setUserDetail(optionalUser.get());
        return subscriptionRepository.save(s);
    }
    
    private List<UserDetail> getNewsletterSubscribers(Long newsletterID)
    {
        return subscriptionRepository
                .findAllByNewsletterId(newsletterID)
                .stream().map(Subscription::getUserDetail)
                .collect(Collectors.toList());
    }
    
    public List<Article> getArticlesInNewsletter(Long newsletterID) throws NewsletterNotFoundException
    {
        Optional<Newsletter> optionalNewsletter = newsletterRepository.findById(newsletterID);
        if(optionalNewsletter.isEmpty()) throw new NewsletterNotFoundException();
        return articleNewsletterRepository
                .findAllByNewsletter(optionalNewsletter.get())
                .stream()
                .map(ArticleNewsletter::getArticle)
                .collect(Collectors.toList());
    }
}
