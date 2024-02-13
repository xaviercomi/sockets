
package FTP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author xcomi
 */
public class Client {
    public static void main(String[] args) throws IOException {
        
        try {
            Scanner lector = new Scanner(System.in);
            
            // obtenir localhost ip, port i 
            InetAddress ip = InetAddress.getByName("localhost");
            int port = 7777;
            
            // establir la conexio
            Socket socket = new Socket(ip, port);
            
            // obtenir fluxos d'entrada i sortida
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            
            // bucle que realitza l'intercanvi de informació entre el client i el gestor de clients
            while (true) {
                System.out.println(dis.readUTF());
                String perEnviar = lector.nextLine();
                dos.writeUTF(perEnviar);
                
                // si el client envia "Exit" surt del bucle
                if (perEnviar.equals("Exit")) {
                    break;
                }
                
                // data o hora d'impressió segons la petició del client.
                String rebut = dis.readUTF();
                System.out.println(rebut);
            }
            
            // tanca recursos
            lector.close();
            dis.close();
            dos.close();
            
            // el sòcol sempre es tanca després que els fluxos de dades.
            System.out.println("Tancant aquesta connexió : " + socket);
            System.out.println("Connexió tancada");
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    
}
