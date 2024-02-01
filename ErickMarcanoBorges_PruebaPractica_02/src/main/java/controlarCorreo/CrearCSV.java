package controlarCorreo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CrearCSV {

    public CrearCSV() {
    }

    public void crearCSV(List<Email> emails) {
        try {
            File archivo = new File("src/main/resources/correos.csv");
            if (archivo.exists()) {
                archivo.delete();
            }
            try {
                FileWriter writer = new FileWriter("src/main/resources/correos.csv");
                writer.append("Asunto,Fecha\n");
                for (Email correo : emails) {
                    writer.append("\"" + correo.getAsunto() + "\",");
                    writer.append("\"" + correo.getFecha() + "\"\n");
                }
                System.out.println("el CSV se generó correctamente.");
            } catch (IOException e) {
                System.out.println("Error escribiendo el CSV: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
