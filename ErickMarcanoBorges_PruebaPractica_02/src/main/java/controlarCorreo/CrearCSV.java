package controlarCorreo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrearCSV {

    public CrearCSV() {}

    public void creacionCSV() throws FileNotFoundException, IOException {
        File archivoTXT = new File("src/main/resources/correos.txt");
        File archivoCSV = new File("src/main/resources/correos.csv");
        RandomAccessFile reader = new RandomAccessFile(archivoTXT, "r");
        BufferedWriter writer = new BufferedWriter(new FileWriter(archivoCSV));
        Object[] datosCorreo = null;

        if (archivoCSV.exists()) {
            archivoCSV.delete();
        }

        writer.write("Asunto,Fecha\n");

        try {
            while (true) {
                datosCorreo = leerCorreo(reader);
                if (datosCorreo == null) {
                    break;
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                writer.write("\"" + datosCorreo[0] + "\",");
                writer.write("\"" + dateFormat.format(datosCorreo[1]) + "\"\n");
            }
        } catch (IOException | ParseException e) {
            System.out.println("Advertencia: " + e.getMessage());
        } finally {
            reader.close();
            writer.close();
            System.out.println("Archivo CSV generado correctamente.");
        }
    }

    private static Object[] leerCorreo(RandomAccessFile reader) throws IOException, ParseException {
        String linea;
        String asunto = null;
        Date fecha = null;

        while ((linea = reader.readLine()) != null) {
            if (linea.startsWith("Asunto:")) {
                asunto = linea.substring("Asunto:".length()).trim();
            } else if (linea.startsWith("Fecha:")) {
                String fechaStr = linea.substring("Fecha:".length()).trim();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                fecha = dateFormat.parse(fechaStr);
            }
            if (asunto != null && fecha != null) {
                return new Object[]{asunto, fecha};
            }
        }
        return null;
    }
}
