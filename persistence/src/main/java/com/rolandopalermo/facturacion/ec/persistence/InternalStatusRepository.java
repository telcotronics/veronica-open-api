package com.rolandopalermo.facturacion.ec.persistence;

import com.rolandopalermo.facturacion.ec.domain.InternalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternalStatusRepository extends JpaRepository<InternalStatus, Long> {
}