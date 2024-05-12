package com.karthik.newsletterapp.exception;

public class NewsletterNotFoundException extends Exception
{
    public NewsletterNotFoundException()
    {
        super("The Newsletter does not exist");
    }
}
