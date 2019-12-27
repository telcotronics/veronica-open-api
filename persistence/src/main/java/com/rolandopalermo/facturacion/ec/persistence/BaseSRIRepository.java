package com.rolandopalermo.facturacion.ec.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseSRIRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    List<T> findByAccessKeyAndIsDeleted(String accessKey, boolean isDeleted);

    List<String> findBySupplierIdAndIsDeleted(String supplierId, boolean isDeleted);

}