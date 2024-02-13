/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor_Aritmetico_TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author xcomi
 */
public class ClienteAritmetico {
    
    public static void main(String[] args) {
        
        String host = "localhost";
        int port = 9999;
        String operacio = "/:777:7";
        String resultat[] = operacio.split(":");
        
        System.out.println("L'operació demanada és una " + resultat[0]
                            + ", més concretament " + resultat[1]
                            + resultat[0] + resultat[2]);
        
        try {
            
            Socket socket = new Socket(host, port);
            
            try (BufferedWriter escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                
                    // Envia la operació
                    escriptor.write(operacio);
                    escriptor.newLine();
                    escriptor.flush();
                    
                    // Obté el resultat del servidor i l'imprimeix
                    System.out.println("El resultat és: " + lector.readLine());

            
            }
            
            socket.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
    
}
