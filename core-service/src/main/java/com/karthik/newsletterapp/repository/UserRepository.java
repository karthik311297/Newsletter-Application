package com.karthik.newsletterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karthik.newsletterapp.model.UserDetail;

@Repository
public interface UserRepository extends JpaRepository<UserDetail, Long>
{
}
