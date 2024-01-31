/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitoreoRam;

import controlarCorreo.Correo;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

/**
 *
 * @author 2damb
 */
public class MonitoreoRAM extends Thread {

    Long ram;
    String correoAdmin;
    String clave;
    boolean alerta = false;

    public MonitoreoRAM(String correo, String clave) {
        this.correoAdmin = correo;
        this.clave = clave;
    }

    @Override
    public void run() {
        int contador = 0;
        SystemInfo sistema = new SystemInfo();
        HardwareAbstractionLayer hardware = sistema.getHardware();
        long ramTotal = hardware.getMemory().getTotal();
        float total = (float) (ramTotal * 9.3132e-10);
        while (true) {
            try {
                ram = hardware.getMemory().getAvailable();
                float disponible = (float) (ram * 9.3132e-10);
                System.out.println(disponible);
                if (contador == 0) {
                   contador = 1;
                   comprobarEstado(disponible, total);
                }
                comprobarEstado(disponible, total);
                if (alerta) {
                    Correo correo = new Correo();
                    correo.enviarMensajeTexto(correoAdmin, "elio0eri@gmail.com", "(PruebaPracticaPGV) Alerta de RAM!!!", "La RAM está en un estado crítico, verifique que", correoAdmin, clave);
                    System.out.println("Email enviado con exito");
                    alerta = false;
                }
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MonitoreoRAM.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(MonitoreoRAM.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MonitoreoRAM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void comprobarEstado(float ramDisponible, float ramTotal) {
        int lineaTotal = (int) (ramTotal * 80) / 100;
        int linea = (int) (ramTotal - lineaTotal);
        System.out.println(linea);
        if (ramDisponible <= linea) {
               alerta = true;
        }
    }

    public Long getRam() {
        return ram;
    }

    public void setRam(Long ram) {
        this.ram = ram;
    }

    public String getCorreoAdmin() {
        return correoAdmin;
    }

    public void setCorreoAdmin(String correoAdmin) {
        this.correoAdmin = correoAdmin;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isAlerta() {
        return alerta;
    }

    public void setAlerta(boolean alerta) {
        this.alerta = alerta;
    }

}
