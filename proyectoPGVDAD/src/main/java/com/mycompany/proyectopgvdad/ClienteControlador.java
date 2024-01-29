/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectopgvdad;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erick
 */
public class ClienteControlador implements Runnable{
    
    private Socket socket;
    
    public ClienteControlador(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            String sentence;
            String modifiedSentence;
            Socket clientSocket = new Socket("localhost", 6789);
            do {
                System.out.print("Escriba el mensaje a enviar: ");
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                sentence = inFromUser.readLine();
                outToServer.writeBytes(sentence + "\n");
                modifiedSentence = inFromServer.readLine();
                System.out.println("DEL SERVIDOR: " + modifiedSentence);
            } while (true);
        } catch (IOException ex) {
            Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
