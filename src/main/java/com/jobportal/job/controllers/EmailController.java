package com.jobportal.job.controllers;

import com.jobportal.job.dtos.responses.EmailDetails;
import com.jobportal.job.service.EmailService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    public String sendEmail(@RequestBody EmailDetails emailDetails,@RequestParam String email) {
        return emailService.sendEmail(emailDetails, email);
    }
}
