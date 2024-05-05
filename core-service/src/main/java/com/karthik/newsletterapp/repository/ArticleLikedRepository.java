package com.karthik.newsletterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthik.newsletterapp.model.Article;
import com.karthik.newsletterapp.model.ArticleLiked;
import com.karthik.newsletterapp.model.identifier.ArticleLikedID;

public interface ArticleLikedRepository extends JpaRepository<ArticleLiked,ArticleLikedID>
{
    long countByArticle(Article article);
}
