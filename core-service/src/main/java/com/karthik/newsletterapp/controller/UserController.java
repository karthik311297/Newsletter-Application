package com.karthik.newsletterapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthik.newsletterapp.model.UserDetail;
import com.karthik.newsletterapp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController
{
    @Autowired
    private UserService userService;
    
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDetail userDetail)
    {
        UserDetail saved = userService.createUser(userDetail);
        return ResponseEntity.ok("UserDetail created with id : " + saved.getId());
    }
}
