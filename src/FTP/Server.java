/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FTP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author xcomi
 */
public class Server {
    
    public static void main(String[] args) throws IOException {
        
        // el servidor escota al port 7777
        ServerSocket serverSocket = new ServerSocket(7777);
        
        int i = 1;
        
        // executant un bucle infinit per obtenir les sol·licituds del client
        System.out.println("Esperant Clients!!!");
        
        while (true) {
            
            Socket socket = null;
            
            try {
                
                // es crea un socket a partir de una sol·licitud d'un client entrant
                socket = serverSocket.accept();
                
                System.out.println("Un nou client s'ha connectat : " + socket + " Client " + i);
                
                i++;
                
                System.out.println("Assignant un nou fil per aquest client");
                
                // creeu un nou objecte de fil
                Thread thread = new ClientHandler(socket);
                
                // Invocant el mètode start() per iniciar el fil
                thread.start();
                        
                        
                
            } catch (IOException e) {
                socket.close();
                e.printStackTrace();
            }
            
        }
        
    }
    
}
