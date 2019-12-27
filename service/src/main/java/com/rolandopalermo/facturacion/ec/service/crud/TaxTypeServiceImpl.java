package com.rolandopalermo.facturacion.ec.service.crud;

import com.rolandopalermo.facturacion.ec.domain.TaxType;
import com.rolandopalermo.facturacion.ec.dto.TipoImpuestoDTO;
import com.rolandopalermo.facturacion.ec.persistence.TaxTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("taxTypeServiceImpl")
public class TaxTypeServiceImpl extends GenericCRUDServiceImpl<TaxType, TipoImpuestoDTO> {

    @Autowired
    private TaxTypeRepository domainRepository;

    @Override
    public TaxType mapTo(TipoImpuestoDTO tipoImpuestoDTO) {
        TaxType taxType = new TaxType();
        taxType.setCode(tipoImpuestoDTO.getCodigo());
        taxType.setDescription(tipoImpuestoDTO.getDescripcion());
        return taxType;
    }

    @Override
    public Optional<TaxType> findExisting(TipoImpuestoDTO domainObject) {
        return domainRepository.findByCode(domainObject.getCodigo());
    }

    @Override
    public TipoImpuestoDTO build(TaxType domainObject) {
        return null;
    }

}