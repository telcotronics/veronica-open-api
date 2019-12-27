package com.rolandopalermo.facturacion.ec.service.sri.v1;

import com.rolandopalermo.facturacion.ec.common.DateUtils;
import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import com.rolandopalermo.facturacion.ec.domain.Bol;
import com.rolandopalermo.facturacion.ec.domain.Consignee;
import com.rolandopalermo.facturacion.ec.dto.v1.bol.GuiaRemisionDTO;
import com.rolandopalermo.facturacion.ec.modelo.guia.GuiaRemision;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static org.exolab.castor.dsml.ImportEventListener.CREATED;

@Service("bolServiceImpl")
public class BolServiceImpl extends GenericSRIServiceImpl<GuiaRemisionDTO, GuiaRemision, Bol> {

    @Override
    public Bol toEntity(GuiaRemision guia, String contentAsXML) throws VeronicaException {
        Bol bol = new Bol();
        bol.setAccessKey(guia.getInfoTributaria().getClaveAcceso());
        bol.setSriVersion(guia.getVersion());
        bol.setXmlContent(contentAsXML);
        bol.setSupplierId(guia.getInfoTributaria().getRuc());
        bol.setShipperRuc(guia.getInfoGuiaRemision().getRucTransportista());
        bol.setRegistrationNumber(guia.getInfoGuiaRemision().getPlaca());
        bol.setIssueDate(DateUtils.getFechaFromStringddMMyyyy(guia.getInfoGuiaRemision().getFechaIniTransporte()));
        bol.setBolNumber(guia.getInfoTributaria().getSecuencial());
        bol.setInternalStatusId(CREATED);
        bol.setConsignees(guia.getDestinatario()
                .stream()
                .map(destinatario -> {
                    Consignee consignee = new Consignee();
                    consignee.setConsignneNumber(destinatario.getIdentificacionDestinatario());
                    consignee.setCustomDocNumber(destinatario.getDocAduaneroUnico());
                    consignee.setReferenceDocCod(destinatario.getCodDocSustento());
                    consignee.setReferenceDocNumber(destinatario.getNumDocSustento());
                    consignee.setReferenceDocAuthNumber(destinatario.getNumAutDocSustento());
                    consignee.setBol(bol);
                    return consignee;
                })
                .collect(Collectors.toList()));
        return bol;
    }

}