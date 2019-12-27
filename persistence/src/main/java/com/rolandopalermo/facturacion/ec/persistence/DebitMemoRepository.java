package com.rolandopalermo.facturacion.ec.persistence;

import com.rolandopalermo.facturacion.ec.domain.DebitMemo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("debitMemoRepository")
public interface DebitMemoRepository extends BaseSRIRepository<DebitMemo, Long> {

    @Override
    @Query("select dm.accessKey from DebitMemo dm where dm.supplierId = ?1 and dm.isDeleted = ?2")
    List<String> findBySupplierIdAndIsDeleted(String supplierId, boolean isDeleted);

}