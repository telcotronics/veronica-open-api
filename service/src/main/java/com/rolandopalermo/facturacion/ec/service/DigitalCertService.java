package com.rolandopalermo.facturacion.ec.service;

import com.rolandopalermo.facturacion.ec.domain.DigitalCert;
import com.rolandopalermo.facturacion.ec.dto.CertificadoDigitalDTO;

import java.util.Optional;

public interface DigitalCertService {

    DigitalCert save(String supplierNumber, CertificadoDigitalDTO dtoObject);

    Optional<DigitalCert> findExisting(String supplierNumber);

    CertificadoDigitalDTO toDTO(DigitalCert domain);

    DigitalCert toEntity(String supplierNumber, CertificadoDigitalDTO dto);

}