package com.rolandopalermo.facturacion.ec.persistence;

import com.rolandopalermo.facturacion.ec.domain.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("invoiceRepository")
public interface InvoiceRepository extends BaseSRIRepository<Invoice, Long> {

    @Override
    @Query("select i.accessKey from Invoice i where i.supplierId = ?1 and i.isDeleted = ?2")
    List<String> findBySupplierIdAndIsDeleted(String supplierId, boolean isDeleted);

}