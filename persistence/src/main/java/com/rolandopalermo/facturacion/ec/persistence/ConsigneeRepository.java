package com.rolandopalermo.facturacion.ec.persistence;

import com.rolandopalermo.facturacion.ec.domain.Consignee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsigneeRepository extends JpaRepository<Consignee, Long> {
}