package com.rolandopalermo.facturacion.ec.service.sri.v1;

import com.rolandopalermo.facturacion.ec.common.DateUtils;
import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import com.rolandopalermo.facturacion.ec.domain.Invoice;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.FacturaDTO;
import com.rolandopalermo.facturacion.ec.modelo.factura.Factura;
import org.springframework.stereotype.Service;

import static org.exolab.castor.dsml.ImportEventListener.CREATED;

@Service("invoiceServiceImpl")
public class InvoiceServiceImpl extends GenericSRIServiceImpl<FacturaDTO, Factura, Invoice> {

    @Override
    public Invoice toEntity(Factura factura, String contentAsXML) throws VeronicaException {
        Invoice invoice = new Invoice();
        invoice.setAccessKey(factura.getInfoTributaria().getClaveAcceso());
        invoice.setSriVersion(factura.getVersion());
        invoice.setXmlContent(contentAsXML);
        invoice.setSupplierId(factura.getInfoTributaria().getRuc());
        invoice.setCustomerId(factura.getInfoFactura().getIdentificacionComprador());
        invoice.setIssueDate(DateUtils.getFechaFromStringddMMyyyy(factura.getInfoFactura().getFechaEmision()));
        invoice.setInvoiceNumber(factura.getInfoTributaria().getSecuencial());
        invoice.setInternalStatusId(CREATED);
        return invoice;
    }

}