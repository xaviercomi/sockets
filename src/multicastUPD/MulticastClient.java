/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multicastUPD;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author xcomi
 */
public class MulticastClient {
    
    final static String INET_ADDR = "224.0.0.7";
    final static int PORT = 8888;
    
    public static void main(String[] args) throws UnknownHostException, SocketException {
        
        // Obtener la direccion a la que nos conectaremos.
        InetAddress addr = InetAddress.getByName(INET_ADDR);
        
        // Obtener la inteficie de red a la que nos queremos unir. 
        NetworkInterface netIf;
        
        try {
            netIf = NetworkInterface.getByName("WiFi");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
       
        
        // Crear un buffer de bytes para almacenar los datos entrantes.
        byte[] buf = new byte[256];
        
        
        try (MulticastSocket socketClient = new MulticastSocket(PORT)) {
            
            // Nos unimos al grupo multicast con la dirección y la interficie de red.
            socketClient.joinGroup(new InetSocketAddress(addr, PORT), netIf);
            
            while (true) {
                
                // Recibimos la información y la mostramos por pantalla.
                DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
                socketClient.receive(msgPacket);
                
                String msg = new String(buf, 0, msgPacket.getLength());
                System.out.println("Mensaje recibido: " + msg);
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        
        
    }
    
}
