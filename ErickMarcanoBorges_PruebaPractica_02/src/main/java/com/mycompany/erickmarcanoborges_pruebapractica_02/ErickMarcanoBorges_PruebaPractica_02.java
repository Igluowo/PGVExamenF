/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.erickmarcanoborges_pruebapractica_02;

import controlarCorreo.ProtocoloIMAP;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import monitoreoRam.MonitoreoRAM;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author 2damb
 */
public class ErickMarcanoBorges_PruebaPractica_02 {

    public static void main(String[] args) throws IOException, MessagingException, JRException {
        String correo = "elio0eri@gmail.com";
        String clave = "hxqdulsfovmarzhd";
        Thread monitoreo = new MonitoreoRAM("elio0eri@gmail.com", "hxqdulsfovmarzhd");
        monitoreo.start();
        ProtocoloIMAP imap = new ProtocoloIMAP(correo, clave);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Asunto", "Quesito");
        parameters.put("Asunto", "Quesitoto");
        parameters.put("Asunto", "Quesitototo");
        parameters.put("Fecha", "aaaa");
        parameters.put("Fecha", "Quesitoto");
        parameters.put("Fecha", "Quesitototo");
        Connection con = null;
        try {
            
            JasperPrint imprimir = JasperFillManager.fillReport("src/main/correosRam.jasper", parameters);
            JasperViewer.viewReport(imprimir, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
