package com.jobportal.job.service.impl;

import com.jobportal.job.dtos.CertificateDto;
import com.jobportal.job.loggers.MainLogger;
import com.jobportal.job.loggers.messages.CertificateMessage;
import com.jobportal.job.model.Certificate;
import com.jobportal.job.repository.CertificateRepository;
import com.jobportal.job.service.CertificateService;
import com.jobportal.job.service.ProfileService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertificateServiceImpl implements CertificateService{
    private final CertificateRepository certificateRepository;
    private final ProfileService profileService;
    private final MainLogger LOGGER = new MainLogger(CertificateService.class);

    public CertificateServiceImpl(CertificateRepository certificateRepository, ProfileService profileService) {
        this.certificateRepository = certificateRepository;
        this.profileService = profileService;
    }

    @Override
    @Transactional
    public String createCertificate(CertificateDto certificateDto) {
        Certificate certificate = toEntity(certificateDto);
        certificate.setProfile(profileService.getProfileEntityById(certificateDto.getProfileId()));
        certificateRepository.save(certificate);
        return CertificateMessage.CERTIFICATE_CREATED + certificate.getId();
    }

    @Override
    public CertificateDto getCertificateById(long id) {
        return toDto(findCertificateById(id));
    }

    @Override
    @Transactional
    public String updateCertificate(long id, CertificateDto certificateDto) {
        Certificate certificate = findCertificateById(id);
        certificate.setCertificateName(certificateDto.getName());
        certificate.setCompanyName(certificateDto.getCompanyName());
        certificate.setCertificateHours(certificateDto.getHours());
        certificate.setCertificateUrl(certificateDto.getUrl());
        certificateRepository.save(certificate);

        return CertificateMessage.CERTIFICATE_UPDATED + id;
    }

    @Override
    @Transactional
    public String deleteCertificate(long id) {
        certificateRepository.deleteById(id);
        return CertificateMessage.CERTIFICATE_DELETED + id;
    }

    @Override
    public List<CertificateDto> getAllCertificateByProfileId(long profileId) {
        profileService.getProfileEntityById(profileId);

        return certificateRepository.findByProfileId(profileId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private Certificate findCertificateById(long id) {
        return certificateRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.log(CertificateMessage.CERTIFICATE_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                });
    }

    private CertificateDto toDto(Certificate certificate) {
        return CertificateDto.builder()
                .name(certificate.getCertificateName())
                .companyName(certificate.getCompanyName())
                .postingDate(certificate.getPostingDate())
                .hours(certificate.getCertificateHours())
                .url(certificate.getCertificateUrl())
                .profileId(certificate.getProfile().getId())
                .build();
    }

    private Certificate toEntity(CertificateDto certificateDto) {
        return Certificate.builder()
                .certificateName(certificateDto.getName())
                .companyName(certificateDto.getCompanyName())
                .postingDate(new Timestamp(System.currentTimeMillis()))
                .certificateHours(certificateDto.getHours())
                .certificateUrl(certificateDto.getUrl())
                .build();
    }
}
