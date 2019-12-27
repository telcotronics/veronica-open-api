package com.rolandopalermo.facturacion.ec.persistence;

import com.rolandopalermo.facturacion.ec.domain.DigitalCert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("digitalCertRepository")
public interface DigitalCertRepository extends JpaRepository<DigitalCert, Long> {

    List<DigitalCert> findByOwnerAndPasswordAndActive(String owner, String password, boolean active);

    Optional<DigitalCert> findByOwnerAndActive(String owner, boolean active);

}