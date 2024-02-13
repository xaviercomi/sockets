
package FTP;

/**
 *
 * @author xcomi
 */

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTP {
    
    public static final String IP = "localhost";
    public static final int PORT = 21;
    public static String USUARI;
    public static String PASSWORD;
    
    public static void main(String[] args) {
        FTPClient clientFTP = null;
        Scanner scanner = new Scanner(System.in);
        
        boolean loginSuccess = false;
        
        while (!loginSuccess) {
            // Demana  l'usuari i la contrasenya fins que siguin correctes
            System.out.println("Introdueix l'usuari: ");
            USUARI = scanner.nextLine();
            System.out.println("Introdueix la contrasenya: ");
            PASSWORD = scanner.nextLine();
            
            try {
                // Conecta amb el servidor FTP i intenta iniciar la sessió
                System.out.println("Connectant i iniciant sessió...");
                clientFTP = new FTPClient();
                clientFTP.connect(IP, PORT);
                
                if (clientFTP.login(USUARI, PASSWORD)) {
                    loginSuccess = true;
                } else {
                    System.out.println("Usuari o contrasenya incorrectes. Torna a intentar-ho.");
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        
        try {
            clientFTP.enterLocalPassiveMode();
            clientFTP.setFileType(FTPClient.BINARY_FILE_TYPE);
            
            menu(clientFTP);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (clientFTP != null) {
                try {
                    System.out.println("Tancant connexió i desconectant-se del servidor...");
                    if (clientFTP.isConnected()) {
                        clientFTP.logout();
                        clientFTP.disconnect();
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        
        
        
    }
    
    public static void menu(FTPClient clientFTP) throws IOException {
        boolean sortir = false;
        Scanner lector = new Scanner(System.in);
        int opcio;
        
        while (!sortir) {
            System.out.println("----MENU----");
            System.out.println("1. Mostrar els directoris i fitxers de la carpeta compartida al FTP");
            System.out.println("2. Descarregar un fitxer");
            System.out.println("3. Borrar un fitxer");
            System.out.println("4. Sortir");
            System.out.print("Escull una opció: ");

            try {
                opcio = lector.nextInt();

                switch(opcio) {
                    case 1:
                        mostrarDirectori(clientFTP);
                        break;
                    case 2:
                        descarregarFitxer(clientFTP);
                        break;
                    case 3:
                        esborrarFitxerFTP(clientFTP);
                        break;
                    case 4:
                        sortir = true;
                        break;
                    default:
                        System.out.println("Opció incorrecta, torna a introduir un número.");          
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Introdueix un número vàlid.");
                lector.nextLine(); // neteja la línia d'entrada incorrecta
            }
        }
        
        lector.close();
        
    }
    
    public static void mostrarDirectori(FTPClient clientFTP) throws IOException {
        System.out.println("Llistant el directori arrel del servidor...");
        FTPFile[] fitxers = clientFTP.listFiles();
        
        for (int i = 0; i < fitxers.length; i++) {
            System.out.println(fitxers[i].getName());
        }
    }
    
    public static void descarregarFitxer(FTPClient clientFTP) throws IOException {
        
        Scanner lector = new Scanner(System.in);
        OutputStream os = null;
        
        System.out.println("Introdueix la ruta completa del fitxer a descarregar: ");
        String fitxerRemot = lector.nextLine();
        
        File fitxerLocal = new File(fitxerRemot);
        
        System.out.println("Descarregant fitxer " + fitxerRemot + " del servidor...");
        // Descarrega un fitxer del servidor FTP
        os = new BufferedOutputStream(new FileOutputStream(fitxerLocal));
        if (clientFTP.retrieveFile(fitxerRemot, os)) {
            System.out.println("El fitxer s'ha rebut correctament.");
            
        } else {
            System.out.println("No s'ha pogut descarregar el fitxer. Verifica la ruta i els permisos.");
        }
        
        os.close();
       
    }
    
    public static void esborrarFitxerFTP(FTPClient clientFTP) throws IOException {
        Boolean transferencia = false;
        Scanner lector = new Scanner(System.in);
        String arxiu;
        
        System.out.println("Introdueix la ruta del fitxer a borrar: ");
        arxiu = lector.next();
        
        // Borrar l'arxiu al servidor
        transferencia = clientFTP.deleteFile(arxiu);
        
        if (transferencia) {
            System.out.println("Arxiu borrat.");
        } else {
            System.out.println("No s'ha pogut borrar l'arxiu, ruta incorrecta o permisos insuficients.");
        }
    } 
    
    
    
}
