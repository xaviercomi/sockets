package sockets;

/**
 *
 * @author xcomi
 */

import java.net.*;
import java.io.*;

public class ClienteTCP {
        
        // método principal de la clase
        public static void main(String[] args) {
            
            // creamos una instancia BufferedReader en la que guardamos los datos
            // introducidos por el usuario
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            
            // declaramos un objeto socket para realizar lka comunicación
            Socket socket;
            
            // declaramos una variable de tipo string
            String mensaje = "";
            
            // declaramos un bloque try para controlar la ejecución del subprograma      
            try {
                
                // instanciamos un socket con la dirección del destino y el 
                // puerto que vamos a utilizar para la comunicación
                socket = new Socket("127.0.0.1", 6000);
                
                // declaramos e instanciamos el objeto DataOutputStream
                // que utilizaremos para enviar datos al servidor destino
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                
                do {
                    mensaje = in.readLine();
                    // enviamos el mensaje codificado en UTF
                    out.writeUTF(mensaje);
                    // mientras el mensaje no encuentre la cadena fin
                    // sigue ejecutando el bucle do-while
                    
                } while (!mensaje.startsWith("fin"));
                
              // utilizamos el catch para capturar los errores   
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        
        }

    
}
