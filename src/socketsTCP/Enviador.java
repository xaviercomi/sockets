
package socketsTCP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author xcomi
 */
public class Enviador {
    
    public static void main(String[] args) {
        
        try {
            // creando socket cliente
            Socket socketCliente = new Socket();
            
            // establecemos la comunicacion 
            InetSocketAddress direccion = new InetSocketAddress("localhost", 5555);
            socketCliente.connect(direccion);
            System.out.println("Connected!");
            
            // creamos un stream de salida
            OutputStream out = socketCliente.getOutputStream();

            // solicitar al usuario la frase a enviar
            // crear un buffer reader para aceptar un input stream reader
            System.out.println("Entra la frase a enviar: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            // enviant la frase 
            String line;
            while((line = br.readLine()) != null) {
                DataOutputStream dataOutStream = new DataOutputStream(out);
                dataOutStream.flush();
                dataOutStream.close();
            }
            
            System.out.println("Closing socket and outputstream.");
            socketCliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
