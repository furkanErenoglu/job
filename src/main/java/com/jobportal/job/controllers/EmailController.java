package com.jobportal.job.controllers;

import com.jobportal.job.dtos.responses.EmailDetails;
import com.jobportal.job.service.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public String sendEmail(@RequestBody EmailDetails emailDetails,@RequestParam String email) {
        return emailService.sendEmail(emailDetails, email);
    }
}
