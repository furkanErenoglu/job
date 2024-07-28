package com.jobportal.job.service.impl;

import com.jobportal.job.dtos.responses.EmailDetails;
import com.jobportal.job.loggers.MainLogger;
import com.jobportal.job.loggers.messages.EmailMessage;
import com.jobportal.job.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private final static MainLogger LOGGER = new MainLogger(EmailServiceImpl.class);
    @Value("${spring.mail.username}")
    private String sender;
    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public String sendEmail(EmailDetails emailDetails, String email) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(email);
            mailMessage.setSubject(emailDetails.getSubject());
            mailMessage.setText(emailDetails.getBody());

            javaMailSender.send(mailMessage);
            System.out.println("Email sent successfully to " + email);
        } catch (Exception e) {
            LOGGER.log(EmailMessage.EMAIL_SENDING_FAILED+ " " + e.getMessage());
            return null;
        }
        return EmailMessage.EMAIL_SENT_SUCCESSFULLY;
    }
}
