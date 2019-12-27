package com.rolandopalermo.facturacion.ec.service.sri.v1;

import com.rolandopalermo.facturacion.ec.common.DateUtils;
import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import com.rolandopalermo.facturacion.ec.domain.CreditMemo;
import com.rolandopalermo.facturacion.ec.dto.v1.cm.NotaCreditoDTO;
import com.rolandopalermo.facturacion.ec.modelo.notacredito.NotaCredito;
import org.springframework.stereotype.Service;

import static org.exolab.castor.dsml.ImportEventListener.CREATED;

@Service("creditMemoServiceImpl")
public class CreditMemoServiceImpl extends GenericSRIServiceImpl<NotaCreditoDTO, NotaCredito, CreditMemo> {

    @Override
    public CreditMemo toEntity(NotaCredito notaCredito, String contentAsXML) throws VeronicaException {
        CreditMemo creditMemo = new CreditMemo();
        creditMemo.setAccessKey(notaCredito.getInfoTributaria().getClaveAcceso());
        creditMemo.setSriVersion(notaCredito.getVersion());
        creditMemo.setXmlContent(contentAsXML);
        creditMemo.setSupplierId(notaCredito.getInfoTributaria().getRuc());
        creditMemo.setCustomerId(notaCredito.getInfoNotaCredito().getIdentificacionComprador());
        creditMemo.setIssueDate(DateUtils.getFechaFromStringddMMyyyy(notaCredito.getInfoNotaCredito().getFechaEmision()));
        creditMemo.setCreditMemoNumber(notaCredito.getInfoTributaria().getSecuencial());
        creditMemo.setInternalStatusId(CREATED);
        return creditMemo;
    }

}