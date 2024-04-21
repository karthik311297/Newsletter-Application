package com.karthik.newsletterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karthik.newsletterapp.model.ArticleNewsletter;
import com.karthik.newsletterapp.model.identifier.ArticleNewsletterIdentifier;

@Repository
public interface ArticleNewsletterRepository extends JpaRepository<ArticleNewsletter,ArticleNewsletterIdentifier>
{
}
