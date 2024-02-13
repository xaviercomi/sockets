package socketsTCP;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author xcomi
 */
public class Receptor {
    public static void main(String[] args) {
        try {
            // Creando socket del servidor
            ServerSocket socketServidor = new ServerSocket();
            
            // Realizar el bind
            InetSocketAddress direccion = new InetSocketAddress("localhost", 5555);
            System.out.println("Esperando conexiones...");
            
            socketServidor.bind(direccion);
            
            // Acepta las conexiones
            Socket nuevoSocket = socketServidor.accept();
            System.out.println("Conexion aceptada!");
            
            // adquiriendo el stream del nuevo socket
            InputStream entrada = nuevoSocket.getInputStream();
            
            // incorporamos a un buffer la entrada 
            BufferedReader br = new BufferedReader(new InputStreamReader(entrada));
            // leemos el buffer y lo agragamos a una variable string
            String line = br.readLine();
            System.out.println(line + "---------------------------------------------");
            // ciclo que continuará leyendo mientras el texto no sea igual a "end"
            while ((line = br.readLine()) != null) {
                line = br.readLine();
                System.out.println(line);
            }
            
            System.out.println("Conexion finalizada");
            
            // cerramos el nuevo socket
            nuevoSocket.close();
            
            // cerramos el socket del servidor
            socketServidor.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
