/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.erickmarcanoborges_pruebapractica_02;

import controlarCorreo.ProtocoloIMAP;
import controlarCorreo.CrearCSV;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import monitoreoRam.MonitoreoRAM;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author 2damb
 */
public class ErickMarcanoBorges_PruebaPractica_02 {

    public static void main(String[] args) throws IOException, MessagingException, JRException, InterruptedException {
        String correo = "elio0eri@gmail.com";
        String clave = "hxqdulsfovmarzhd";
        Thread monitoreo = new MonitoreoRAM("elio0eri@gmail.com", "hxqdulsfovmarzhd");
        monitoreo.start();
        ProtocoloIMAP imap = new ProtocoloIMAP(correo, clave);
        Thread.sleep(5000);
        JRCsvDataSource csv = new JRCsvDataSource(new File("src/main/resources/correos.csv"));
        try {
            JasperPrint imprimir = JasperFillManager.fillReport("src/main/resources/correos.jasper", null, csv);
            JasperViewer.viewReport(imprimir, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
