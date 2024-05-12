package com.karthik.newsletterapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karthik.newsletterapp.model.Article;
import com.karthik.newsletterapp.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>
{
    List<Comment> findAllByArticle(Article article);
}
