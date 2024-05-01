package com.karthik.newsletterapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthik.newsletterapp.model.UserDetail;
import com.karthik.newsletterapp.repository.UserRepository;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    
    public UserDetail createUser(UserDetail userDetail)
    {
        return userRepository.save(userDetail);
    }
}
