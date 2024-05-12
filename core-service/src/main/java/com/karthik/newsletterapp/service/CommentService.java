package com.karthik.newsletterapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthik.newsletterapp.controller.requests.CommentRequest;
import com.karthik.newsletterapp.exception.ArticleNotFoundException;
import com.karthik.newsletterapp.model.Article;
import com.karthik.newsletterapp.model.Comment;
import com.karthik.newsletterapp.repository.ArticleRepository;
import com.karthik.newsletterapp.repository.CommentRepository;

@Service
public class CommentService
{
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private ArticleRepository articleRepository;
    
    public Comment addCommentToArticle(String commentContent, Long articleID) throws ArticleNotFoundException
    {
        Comment comment = new Comment();
        comment.setContent(commentContent);
        Optional<Article> optionalArticle = articleRepository.findById(articleID);
        if(optionalArticle.isEmpty()) throw new ArticleNotFoundException();
        comment.setArticle(optionalArticle.get());
        return commentRepository.save(comment);
    }
    
    public List<String> getCommentsOnArticle(Long articleID) throws ArticleNotFoundException
    {
        Optional<Article> optionalArticle = articleRepository.findById(articleID);
        if(optionalArticle.isEmpty()) throw new ArticleNotFoundException();
        return commentRepository.findAllByArticle(optionalArticle.get())
                .stream()
                .map(Comment::getContent)
                .collect(Collectors.toList());
    }
}
