package com.karthik.newsletterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karthik.newsletterapp.model.Newsletter;

@Repository
public interface NewsletterRepository extends JpaRepository<Newsletter, Long>
{
}
