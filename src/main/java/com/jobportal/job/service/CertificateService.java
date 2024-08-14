package com.jobportal.job.service;

import com.jobportal.job.dtos.CertificateDto;

import java.util.List;

public interface CertificateService {
    String createCertificate(CertificateDto certificateDto);
    CertificateDto getCertificateById(long id);
    String updateCertificate(long id, CertificateDto certificateDto);
    String deleteCertificate(long id);
    List<CertificateDto> getAllCertificateByProfileId(long profileId);
}
