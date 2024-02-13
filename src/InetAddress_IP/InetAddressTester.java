
package InetAddress_IP;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author xcomi
 */
public class InetAddressTester {
    
    String obtenerIP() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        System.out.println("El nombre del host es " + ip.getHostName());
        return ip.getHostAddress();
    }
    
    public static void main(String[] args) {
        try {
            InetAddressTester checkIP = new InetAddressTester();
            System.out.println("La IP de su computadora es " + checkIP.obtenerIP());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }
  
  
    
}
