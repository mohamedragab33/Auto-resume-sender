package com.resume.sender.resume.sender.controllers;

import com.resume.sender.resume.sender.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public String sendEmails(@RequestBody List<String> toEmails) {
        String subject = "Application for Java Developer Position";
        String message = "Dear Hiring Manager,\n\n" +
                "I hope this message finds you well. " +
                "My name is Mohamed, and I am a Senior Java Developer with over 4 years of experience in designing and developing scalable applications. " +
                "I am proficient in Java, Spring, Microservices, and RESTful APIs, and I have a strong background in working with various database technologies.\n\n" +
                "I am writing to express my interest in any open Java development positions within your esteemed organization. " +
                "I am eager to contribute my skills to your team and help build innovative solutions.\n\n" +
                "Thank you for considering my application. I look forward to the opportunity to discuss my qualifications further.\n\n" +
                "Best regards,\n" +
                "Mohamed Elkazzaz";
        try {
            File resume = new ClassPathResource("Mohamed_Elkazzaz_Resume.pdf").getFile();
            mailService.sendEmailWithAttachment(toEmails, subject, message, resume);
            return "Emails sent successfully!";
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            return "Error occurred while sending emails.";
        }
    }
}
