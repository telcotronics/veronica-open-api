package com.rolandopalermo.facturacion.ec.persistence;

import com.rolandopalermo.facturacion.ec.domain.Withholding;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("withholdingRepository")
public interface WithholdingRepository extends BaseSRIRepository<Withholding, Long> {

    @Override
    @Query("select wh.accessKey from Withholding wh where wh.supplierId = ?1 and wh.isDeleted = ?2")
    List<String> findBySupplierIdAndIsDeleted(String supplierId, boolean isDeleted);

}