package com.karthik.emailservice;

import java.util.List;

public interface EmailService
{
    boolean sendMail(List<String> to, String subject, String body);
}
