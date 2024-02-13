/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multicastUPD;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author xcomi
 */
public class MulticastServer {
    
    final static String INET_ADDR = "224.0.0.7";
    final static int PORT = 8888;
    
    public static void main(String[] args) throws UnknownHostException, InterruptedException, SocketException, IOException {
        
        // Obtener la dirección a la qual nos conectaremos.
        InetAddress addr = InetAddress.getByName(INET_ADDR);
        
        // Crear un DatagramSocket que se utilizará para enviar los datos.
        try (DatagramSocket serverSocket = new DatagramSocket()) {
            
            // Crear una matriz con las playas
            String[] codigoPlaya = {"Barceloneta", "Bogatell", "Cabrera", "Calade-Santa-Cristina" };
            
            while (true) {
                String playaMasVentosa = "";
                int vientoMasFuerte = -1;
                
                List<Future<Integer>> resultados = new ArrayList<>();
                
                ExecutorService exs = Executors.newFixedThreadPool(8);
                
                for (String codPlaya : codigoPlaya) {
                    
                    // Consulta la  velocidad del viento en la playa actual con un hilo.
                    Future<Integer> resultado = exs.submit(() -> consultarVelocidadViento(codPlaya));
                    resultados.add(resultado);
                    
                }
                
                exs.shutdown();
                exs.awaitTermination(4, TimeUnit.MINUTES);      
                
                for (int i = 0; i < codigoPlaya.length; i++) {
                    
                    int viento = resultados.get(i).get();
                    
                    if (viento > vientoMasFuerte) {
                        vientoMasFuerte = viento;
                        playaMasVentosa = codigoPlaya[i];
                    }
                    
                    enviarMensajeMulticast(serverSocket, addr, playaMasVentosa, vientoMasFuerte);
                    
                    // Esperar 5 segundos antes de enviar el siguiente mensaje.
                    Thread.sleep(5000);
                }
                
            }
            
        } catch ( IOException | InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
        
    }
    

    public static int consultarVelocidadViento(String codPlaya) {
        try {
            Document doc = Jsoup.connect("http://es.surf-forecast.com/breaks/" + codPlaya + "/forecasts/feed/m").get();
            Elements e = doc.select("#table-wind");
            String km = e.text();
            String[] kms = km.split(" ");
            String primero = kms[2];
            return Integer.parseInt(primero);
        } catch (Exception e) {
            e.printStackTrace(); 
            return -1;
        }
        
    }
    
    public static void enviarMensajeMulticast(DatagramSocket serverSocket, InetAddress addr, String playaMasVentosa, int vientoMasFuerte) throws IOException {
        String msg = "Playa con mas viento: " + playaMasVentosa + ", viento: " + vientoMasFuerte + " km/h";
        DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, addr, PORT);
        serverSocket.send(msgPacket);
        System.out.println("Enviando paquete con mensaje desde el servidor: " + msg);
    }
    
}
