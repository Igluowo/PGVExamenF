/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlarCorreo;

/**
 *
 * @author erick
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SubjectTerm;

public class ProtocoloIMAP {

    public ProtocoloIMAP(String correo, String clave) throws IOException, MessagingException {
        Properties propiedades = new Properties();
        propiedades.put("mail.store.protocol", "imap");
        propiedades.put("mail.imap.host", "imap.gmail.com");
        propiedades.put("mail.imap.port", "993");
        propiedades.put("mail.imap.starttls.enable", "true");
        propiedades.put("mail.imap.ssl.enable", "true");
        Session sesion = Session.getInstance(propiedades);
        Store store = sesion.getStore("imap");
        store.connect(propiedades.getProperty("mail.imap.host"), correo, clave);
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);
        SubjectTerm prefijo = new SubjectTerm("(PruebaPracticaPGVERMB)");
        Message[] correos = folder.search(prefijo);
        String rutaArchivo = "src/main/resources/correos.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo));

        for (Message elemento : correos) {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            writer.write("Asunto: " + elemento.getSubject());
            writer.newLine();
            writer.write("Fecha: " + formatoFecha.format(elemento.getSentDate()));
            writer.newLine();
            writer.write("Cuerpo: " + elemento.getContent().toString());
            writer.newLine();

            System.out.println("Datos escritos en el archivo con éxito.");
        }
        writer.close();
        folder.close(false);
        store.close();
        CrearCSV crear = new CrearCSV();
        crear.creacionCSV();
    }
}
