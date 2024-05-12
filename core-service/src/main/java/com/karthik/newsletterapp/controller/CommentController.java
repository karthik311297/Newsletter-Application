package com.karthik.newsletterapp.controller;

import java.util.ArrayList;
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

import com.karthik.newsletterapp.controller.requests.CommentRequest;
import com.karthik.newsletterapp.exception.ArticleNotFoundException;
import com.karthik.newsletterapp.model.Article;
import com.karthik.newsletterapp.model.Comment;
import com.karthik.newsletterapp.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController
{
    @Autowired
    private CommentService commentService;
    
    @PostMapping
    public ResponseEntity<String> commentOnArticle(@RequestBody CommentRequest commentRequest)
    {
        try
        {
            commentService.addCommentToArticle(commentRequest.getCommentContent(),
                    commentRequest.getArticleID());
            return ResponseEntity.ok("Commented on Article!");
        }
        catch(ArticleNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @GetMapping("/article/{articleID}")
    public ResponseEntity<List<String>> getCommentsOnArticle(@PathVariable Long articleID)
    {
        List<String> comments = new ArrayList<>();
        try
        {
            comments = commentService.getCommentsOnArticle(articleID);
        }
        catch(ArticleNotFoundException e)
        {
        }
        return ResponseEntity.ok(comments);
    }
}
