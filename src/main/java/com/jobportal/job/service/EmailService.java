package com.jobportal.job.service;

import com.jobportal.job.dtos.responses.EmailDetails;

public interface EmailService {
    String sendEmail(EmailDetails emailDetails, String email);
}
