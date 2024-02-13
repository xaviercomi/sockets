/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets;

/**
 *
 * @author xcomi
 */

// importar la libreria java.net y java.io
import java.net.*;
import java.io.*;

// declaramos la classe del servidor TCP

public class ServidorTCP {
    
    // metodo principal main de la clase
    public static void main(String[] args) {
        
        //declaramos un objeto ServerSocket para realizar la comunicacion
        ServerSocket socket;
        
        // creamos una variable boolean con el valor a false
        boolean fin = false;
        
        
        // declaramos un bloque try u catch para controlar la ejecucion del subprograma
        try {
            
            // instanciamos un ServerSocket con la dirección del destino y el 
            // puerto que vamos a utilizar para la comunicación
            
            socket = new ServerSocket(6000);
            
            // creamos un socket al que le vamos a pasar el contenido del objeto socket 
            // despues de ejecutar la funcion accept que nos permitirá aceptar conexiones de clientes
            
            Socket socket_cli = socket.accept();
            
            // Declaramos  e instanciamos el objeto DataInputStream
            // que nos valdrá para recibir datos del cliente
            
            DataInputStream in = new DataInputStream(socket_cli.getInputStream());
            
            // creamos un bucle para recoger el mensaje que envia el cliente
            // y lo mostramos por consola
            
            do {
                String mensaje = "";
                mensaje = in.readUTF();
                System.out.println(mensaje);
            } while (1>0);
            
            
        } catch (Exception e) {
            
            // si presenta errores los mostrará por pantalla en consola y despúes
            // saldrá del programa
            System.out.println(e.getMessage());
            System.exit(1);
            
        }
            
    }
    
}
