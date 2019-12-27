package com.rolandopalermo.facturacion.ec.service.sri;

import com.rolandopalermo.facturacion.ec.common.exception.ResourceNotFoundException;
import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import com.rolandopalermo.facturacion.ec.domain.BaseSRIEntity;
import com.rolandopalermo.facturacion.ec.dto.ComprobanteIdDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.ComprobanteDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.ListaComprobantesDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.sri.RespuestaComprobanteDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.sri.RespuestaSolicitudDTO;
import com.rolandopalermo.facturacion.ec.modelo.Comprobante;

public interface GenericSRIService<DTO extends ComprobanteDTO, MODEL extends Comprobante, DOMAIN extends BaseSRIEntity> {

    ComprobanteIdDTO create(DTO domainObject) throws VeronicaException, ResourceNotFoundException;

    RespuestaSolicitudDTO post(String accessKey) throws VeronicaException;

    RespuestaComprobanteDTO apply(String accessKey) throws ResourceNotFoundException, VeronicaException;

    void delete(String accessKey);

    String getXML(String accessKey);

    byte[] getPDF(String accessKey) throws ResourceNotFoundException, VeronicaException;

    ListaComprobantesDTO findAllBySupplierId(String supplierId);

    DOMAIN toEntity(MODEL modelObject, String contentAsXML) throws VeronicaException;

}