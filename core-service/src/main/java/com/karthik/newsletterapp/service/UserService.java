package com.karthik.newsletterapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthik.newsletterapp.model.User;
import com.karthik.newsletterapp.repository.UserRepository;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    
    public User createUser(User user)
    {
        return userRepository.save(user);
    }
}
