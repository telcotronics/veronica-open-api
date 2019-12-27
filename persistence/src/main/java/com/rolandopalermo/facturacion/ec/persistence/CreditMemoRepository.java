package com.rolandopalermo.facturacion.ec.persistence;

import com.rolandopalermo.facturacion.ec.domain.CreditMemo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("creditMemoRepository")
public interface CreditMemoRepository extends BaseSRIRepository<CreditMemo, Long> {

    @Override
    @Query("select cm.accessKey from CreditMemo cm where cm.supplierId = ?1 and cm.isDeleted = ?2")
    List<String> findBySupplierIdAndIsDeleted(String supplierId, boolean isDeleted);

}