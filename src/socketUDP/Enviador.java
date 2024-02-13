/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socketUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 *
 * @author xcomi
 */
public class Enviador {
    
    public static void main(String[] args) {
        try {
            // Creando socket datagrama
            InetSocketAddress direccion = new InetSocketAddress("localhost", 5556);
            DatagramSocket datagramSocket = new DatagramSocket(direccion);
            System.out.println("Conectado!");
            
            // Enviando el mensaje
            String pregunta = "Cual es tu serie favorita?";
            System.out.println("Enviando mensaje: " + pregunta);
            InetAddress direccionServer = InetAddress.getByName("localhost");
            DatagramPacket packOUT = new DatagramPacket(pregunta.getBytes(), pregunta.getBytes().length, direccionServer, 5555); 
            datagramSocket.send(packOUT);
            
            // Recibiendo un mensaje
            byte[] mensaje = new byte[32];
            DatagramPacket packIN = new DatagramPacket(mensaje, 32);
            datagramSocket.receive(packIN);
            String respuesta = new String(mensaje);
            
            System.out.println("Pregunta recibida: " + pregunta);
            System.out.println("Resposta enviada: " + respuesta);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
