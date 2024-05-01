package com.karthik.newsletterapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karthik.newsletterapp.model.Subscription;
import com.karthik.newsletterapp.model.identifier.SubscriptionID;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,SubscriptionID>
{
    List<Subscription> findByNewsletterId(Long newsletterID);
}
