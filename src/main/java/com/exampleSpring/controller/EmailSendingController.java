package com.exampleSpring.controller;


import com.exampleSpring.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/email")
public class EmailSendingController {

    @Autowired
    private EmailService emailService;

    // for sending email we don't need to create two separate api like with attachment or without attachment
    // for practicing created two api.

    @PostMapping("/send")
    public String sendEmail(
            @RequestParam("to") String recipient,
            @RequestParam("subject") String subject,
            @RequestParam("body") String body) {
        try {
            emailService.sendEmail(recipient, subject, body);
            return String.format("Email sent successfully to '%s' with subject '%s'.", recipient, subject);
        } catch (Exception e) {
            return "Error while sending email: " + e.getMessage();
        }
    }


    @PostMapping("/send/attachment")
    public String sendEmailWithAttachment(
            @RequestParam("to") String recipient,
            @RequestParam("subject") String subject,
            @RequestParam("body") String body,
            @RequestParam(value = "attachment", required = false) MultipartFile attachment) {

        try {
            emailService.sendEmailWithAttachment(recipient, body, subject, attachment);
            return String.format("Email sent successfully to '%s' with subject '%s'.", recipient, subject);
        } catch (Exception e) {
            return "Error while sending email: " + e.getMessage();
        }
    }

}
