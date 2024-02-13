/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FTP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author xcomi
 */
public class ClientHandler extends Thread {
    
    DateFormat fordate = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    DateFormat forday = new SimpleDateFormat("EEEEE:MMMMM:yyyy");
    
    final Socket SOCKET;
    
    // constructor 
    public ClientHandler(Socket SOCKET) {
        this.SOCKET = SOCKET;
    }
    
    @Override
    public void run() {
        
        // obtenir fluxos d'entrada i sortida del sòcol.
        DataInputStream dis = null;
        DataOutputStream dos = null;
        
        try {
            dis = new DataInputStream(SOCKET.getInputStream());
            dos = new DataOutputStream(SOCKET.getOutputStream());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String rebut;
        String toReturn;
        
        while (true) {
            
            try {
                // Pregunta a l'usuari que vol?
                dos.writeUTF("Que vols? [Date | Time | Day].." + "Escriu Exit per acabar la connexio");
                
                // rebem la resposta del client
                rebut = dis.readUTF();
                
                if (rebut.equals("Exit")) {
                    System.out.println("Client " + this.SOCKET + " envia exit...");
                    break;
                }
                
                // Creant Object Data
                Date date = new Date();
                
                // escriure al flux de sortida basat en resposta del client
                
                switch (rebut) {
                    
                    case "Date":
                        toReturn = fordate.format(date);
                        dos.writeUTF(toReturn);
                        break;
                    
                    case "Time":
                        toReturn = fortime.format(date);
                        dos.writeUTF(toReturn);
                        break;
                    
                    case "Day":
                        toReturn = forday.format(date);
                        dos.writeUTF(toReturn);
                        break;
                        
                    default:
                        System.out.println("Input invalid");
                        break;
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        
        try {
            //Tancant recursos
            dis.close();
            dos.close();
            System.out.println("Tanca aquesta connexio.");
            SOCKET.close();
            System.out.println("Connexió tancada");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
