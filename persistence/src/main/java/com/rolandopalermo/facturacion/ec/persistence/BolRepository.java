package com.rolandopalermo.facturacion.ec.persistence;

import com.rolandopalermo.facturacion.ec.domain.Bol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bolRepository")
public interface BolRepository extends BaseSRIRepository<Bol, Long> {

    @Override
    @Query("select b.accessKey from Bol b where b.supplierId = ?1 and b.isDeleted = ?2")
    List<String> findBySupplierIdAndIsDeleted(String supplierId, boolean isDeleted);

}