package com.rolandopalermo.facturacion.ec.service.sri.v1;

import com.rolandopalermo.facturacion.ec.common.DateUtils;
import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import com.rolandopalermo.facturacion.ec.domain.DebitMemo;
import com.rolandopalermo.facturacion.ec.dto.v1.dm.NotaDebitoDTO;
import com.rolandopalermo.facturacion.ec.modelo.notadebito.NotaDebito;
import org.springframework.stereotype.Service;

import static org.exolab.castor.dsml.ImportEventListener.CREATED;

@Service("debitMemoServiceImpl")
public class DebitMemoServiceImpl extends GenericSRIServiceImpl<NotaDebitoDTO, NotaDebito, DebitMemo> {

    @Override
    public DebitMemo toEntity(NotaDebito notaDebito, String contentAsXML) throws VeronicaException {
        DebitMemo creditMemo = new DebitMemo();
        creditMemo.setAccessKey(notaDebito.getInfoTributaria().getClaveAcceso());
        creditMemo.setSriVersion(notaDebito.getVersion());
        creditMemo.setXmlContent(contentAsXML);
        creditMemo.setSupplierId(notaDebito.getInfoTributaria().getRuc());
        creditMemo.setCustomerId(notaDebito.getInfoNotaDebito().getIdentificacionComprador());
        creditMemo.setIssueDate(DateUtils.getFechaFromStringddMMyyyy(notaDebito.getInfoNotaDebito().getFechaEmision()));
        creditMemo.setDebitMemoNumber(notaDebito.getInfoTributaria().getSecuencial());
        creditMemo.setInternalStatusId(CREATED);
        return creditMemo;
    }

}