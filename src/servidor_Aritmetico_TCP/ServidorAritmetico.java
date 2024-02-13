
package servidor_Aritmetico_TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorAritmetico {
    // 
    protected ServicioAritmeticoImplementado servei;

    public void setServei(ServicioAritmeticoImplementado servei) {
        this.servei = servei;
    }

    public void executa(Socket socket) {
        try (BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
        	
            String calcula = lector.readLine();
            
            double result = analitza(calcula);
            
            escriptor.write("" + result);
            escriptor.newLine();
            escriptor.flush();
            
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private double analitza(String operacio) throws IllegalArgumentException {
        double resultat = Double.MAX_VALUE;
        String[] elements = operacio.split(":");
        if (elements.length != 3) {
            throw new IllegalArgumentException("error d'anàlisi");
        }
        double valor1 = 0;
        double valor2 = 0;
        try {
            valor1 = Double.parseDouble(elements[1]);
            valor2 = Double.parseDouble(elements[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Arguments invàlids");
        }
        switch (elements[0].charAt(0)) {
            case '+':
                resultat = servei.suma(valor1, valor2);
                break;
            case '-':
                resultat = servei.resta(valor1, valor2);
                break;
            case '*':
                resultat = servei.mult(valor1, valor2);
                break;
            case '/':
                resultat = servei.div(valor1, valor2);
                break;
            default:
                throw new IllegalArgumentException("Operació invàlida!");
        }
        return resultat;
    }

    public static void main(String[] args) {
    	
		int port = 9999;
		
		ServidorAritmetico servidor = new ServidorAritmetico();
		servidor.setServei(new ServicioAritmeticoImplementado());
		
		try {
		
			System.out.println("El servidor matemàtic està executant...");
			ServerSocket serverSocket = new ServerSocket(port);

			while (true) {
			
				System.out.println("Esperant clients ...");
				Socket socket = serverSocket.accept();
				
				System.out.println("Client rebut");
				servidor.executa(socket);
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			System.exit(1);
		}
    }
}