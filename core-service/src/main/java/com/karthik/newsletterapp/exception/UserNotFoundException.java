package com.karthik.newsletterapp.exception;

public class UserNotFoundException extends Exception
{
    public UserNotFoundException()
    {
        super("The User does not exist");
    }
}
