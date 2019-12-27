package com.rolandopalermo.facturacion.ec.service;

import com.rolandopalermo.facturacion.ec.common.exception.ConflictException;
import com.rolandopalermo.facturacion.ec.domain.DigitalCert;
import com.rolandopalermo.facturacion.ec.dto.CertificadoDigitalDTO;
import com.rolandopalermo.facturacion.ec.persistence.DigitalCertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class DigitalCertServiceImpl implements DigitalCertService {

    @Autowired
    private DigitalCertRepository digitalCertRepository;

    @Override
    public DigitalCert save(String supplierNumber, CertificadoDigitalDTO dtoObject) {
        Optional<DigitalCert> optional = findExisting(supplierNumber);
        if (!optional.isPresent()) {
            DigitalCert domainObject = toEntity(supplierNumber, dtoObject);
            return digitalCertRepository.save(domainObject);
        } else {
            throw new ConflictException(String.format("Ya existe un certificado digital asociado al R.U.C. [%s]", supplierNumber));
        }
    }

    @Override
    public Optional<DigitalCert> findExisting(String supplierNumber) {
        return digitalCertRepository.findByOwnerAndActive(supplierNumber, true);
    }

    @Override
    public CertificadoDigitalDTO toDTO(DigitalCert domain) {
        CertificadoDigitalDTO dto = new CertificadoDigitalDTO();
        dto.setCertificado(domain.getDigitalCert());
        dto.setPassword(domain.getPassword());
        return dto;
    }

    @Override
    public DigitalCert toEntity(String supplierNumber, CertificadoDigitalDTO dto) {
        DigitalCert cert = new DigitalCert();
        cert.setActive(true);
        cert.setInsertDate(new Date());
        cert.setDigitalCert(dto.getCertificado());
        cert.setOwner(supplierNumber);
        cert.setPassword(dto.getPassword());
        return cert;
    }

}