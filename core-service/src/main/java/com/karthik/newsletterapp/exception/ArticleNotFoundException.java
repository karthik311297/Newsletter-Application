package com.karthik.newsletterapp.exception;

public class ArticleNotFoundException extends Exception
{
    public ArticleNotFoundException()
    {
        super("The Article does not exist");
    }
}
