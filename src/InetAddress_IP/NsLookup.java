
package InetAddress_IP;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

/**
 *
 * @author xcomi
 */


public class NsLookup {
    
    public static void main(String[] args) {
        
        Pattern p;
        NsLookup prog;
        
        if (args.length == 0) {
            System.out.println("Error de sintaxi");
            return;
        } 
        
        prog = new NsLookup();
        String param = args[0];
        
        try {
            if (prog.isIpAddress(param)) {
                prog.showHost(param);
            } else {
                prog.showIps(param);
            }
        } catch (UnknownHostException ex) {
            System.out.println("Error, host desconegut: " + args[0]);
        }
        
    }
    
    private void showIps(String host) throws UnknownHostException {
        InetAddress[] addresses = InetAddress.getAllByName(host);
        System.out.println("Answer:");
        for (InetAddress in: addresses) {
            System.out.println(" Name:\t" + in.getHostName());
            System.out.println(" Address:\t" + in.getHostAddress());
        }
    }
    
    private void showHost(String ip) throws UnknownHostException {
        InetAddress address = InetAddress.getByName(ip);
        System.out.println("Answer:");
        System.out.println(" Name:\t" + address.getHostName());
        System.out.println(" Address:\t" + address.getHostAddress());
    }
    
    private boolean isIpAddress(String cad) {
        return true;
    }
    
}
