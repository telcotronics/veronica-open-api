package com.rolandopalermo.facturacion.ec.persistence;

import com.rolandopalermo.facturacion.ec.domain.TaxType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxTypeRepository extends JpaRepository<TaxType, Long> {

    Optional<TaxType> findByCode(String code);

}