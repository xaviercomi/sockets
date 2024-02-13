/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socketUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author xcomi
 */
public class Receptor {
    public static void main(String[] args) {
        
        // Creant socket datagrama
        try {
            InetSocketAddress direccion = new InetSocketAddress("localhost", 5555);
            DatagramSocket datagramSocket = new DatagramSocket(direccion);
            
            System.out.println("Esperando conexiones...");
            
            while (true) {
                // Recibiendo mensaje
                byte[] mensaje = new byte[32];
                DatagramPacket packIN = new DatagramPacket(mensaje, 32);
                datagramSocket.receive(packIN);
                String pregunta = new String(mensaje, StandardCharsets.UTF_8);
                System.out.println("Pregunta recibida: " + pregunta);
                
                if (pregunta.equals("Cual es tu serie favorita?")) {
                    
                    // Enviando mensaje
                    String respuesta = "Black List";
                    DatagramPacket packOUT = new DatagramPacket(respuesta.getBytes(), respuesta.getBytes().length, packIN.getAddress(), packIN.getPort());
                    datagramSocket.send(packOUT);
                }
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
