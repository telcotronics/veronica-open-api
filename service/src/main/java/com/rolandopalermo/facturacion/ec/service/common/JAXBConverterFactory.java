package com.rolandopalermo.facturacion.ec.service.common;

import com.rolandopalermo.facturacion.ec.common.types.DocumentEnum;
import com.rolandopalermo.facturacion.ec.jaxb.JaxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JAXBConverterFactory {

    private List<JaxbService> jaxbCconverterList;

    @Autowired
    public JAXBConverterFactory(List<JaxbService> jaxbCconverterList) {
        this.jaxbCconverterList = jaxbCconverterList;
    }

    public JaxbService get(DocumentEnum tipoDocumento) {
        return jaxbCconverterList
                .stream()
                .filter(service -> service.supports(tipoDocumento))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}