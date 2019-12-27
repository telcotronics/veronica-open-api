package com.rolandopalermo.facturacion.ec.service.common;

import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class SignerUtils {

    public static byte[] signXML(byte[] cotenido, byte[] certificado, String passwordCertificado)
            throws VeronicaException {
        try {
            // Actividad 1.- Generar archivo temporales para el XML, certificado y el
            // archivo firmado
            String rutaArchivoXML = UUID.randomUUID().toString();
            File temp = File.createTempFile(rutaArchivoXML, ".xml");
            rutaArchivoXML = temp.getAbsolutePath();

            String rutaArchivoCertificado = UUID.randomUUID().toString();
            File tempCertificado = File.createTempFile(rutaArchivoXML, ".p12");
            rutaArchivoCertificado = tempCertificado.getAbsolutePath();

            String rutaArchivoXMLFirmado = UUID.randomUUID().toString();
            File tempFirmado = File.createTempFile(rutaArchivoXMLFirmado, ".xml");
            rutaArchivoXMLFirmado = tempFirmado.getAbsolutePath();
            // Actividad 2.- Guardar datos en archivo xml
            try (FileOutputStream fos = new FileOutputStream(rutaArchivoXML)) {
                fos.write(cotenido);
            }
            // Actividad 3.- Guardar certificado
            try (FileOutputStream fos = new FileOutputStream(rutaArchivoCertificado)) {
                fos.write(certificado);
            }
            // Actividad 4.- Firmar el archivo xml creado temporalmente
            Signer firmador = new Signer(rutaArchivoXML, rutaArchivoXMLFirmado, rutaArchivoCertificado,
                    passwordCertificado);
            firmador.firmar();
            // 5.- Obtener el contenido del archivo XML
            Path path = Paths.get(rutaArchivoXMLFirmado);
            byte[] data = Files.readAllBytes(path);
            if (!temp.delete() || !tempFirmado.delete() || !tempCertificado.delete()) {
                throw new VeronicaException("No se pudo eliminar los archivos temporales.");
            }
            return data;
        } catch (IOException | VeronicaException e) {
            throw new VeronicaException(e.getMessage());
        }
    }

}
