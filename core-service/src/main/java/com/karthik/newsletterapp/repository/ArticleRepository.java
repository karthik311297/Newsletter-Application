package com.karthik.newsletterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karthik.newsletterapp.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>
{
}
