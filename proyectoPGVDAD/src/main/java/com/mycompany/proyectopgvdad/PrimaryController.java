package com.mycompany.proyectopgvdad;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class PrimaryController implements Initializable{
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            int port = 6789;
            ServerSocket socket = new ServerSocket(port);
            while(true) {
                Socket conecctionSocket = socket.accept();
                Runnable cliente = new ClienteControlador(conecctionSocket);
                Thread clienteHilo = new Thread(cliente);
                clienteHilo.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
