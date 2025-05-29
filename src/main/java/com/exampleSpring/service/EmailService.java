package com.exampleSpring.service;

import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
    public void sendEmail(String to, String subject, String body);
    public void sendEmailWithAttachment(String recipient, String body, String subject, MultipartFile attachment) throws Exception;
}
