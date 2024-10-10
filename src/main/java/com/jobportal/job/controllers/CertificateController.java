package com.jobportal.job.controllers;

import com.jobportal.job.dtos.CertificateDto;
import com.jobportal.job.service.CertificateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {
    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping
    public String createCertificate(@RequestBody CertificateDto certificateDto) {
        return certificateService.createCertificate(certificateDto);
    }

    @PutMapping("/{id}")
    public String updateCertificate(@PathVariable long id,@RequestBody CertificateDto certificateDto) {
        return certificateService.updateCertificate(id,certificateDto);
    }

    @DeleteMapping("/{id}")
    public String deleteCertificate(@PathVariable Long id) {
        return certificateService.deleteCertificate(id);
    }

    @GetMapping("/{id}")
    public CertificateDto getCertificateById(@PathVariable Long id) {
        return certificateService.getCertificateById(id);
    }

    @GetMapping
    public List<CertificateDto> getAllCertificates(long profileId) {
        return certificateService.getAllCertificateByProfileId(profileId);
    }
}
