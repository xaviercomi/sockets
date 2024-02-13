/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor_Aritmetico_TCP;

/**
 *
 * @author xcomi
 */
public class ServicioAritmeticoImplementado implements ServicioAritmetico {

    @Override
    public double suma(double valor1, double valor2) {
        return valor1 + valor2;
    }

    @Override
    public double resta(double valor1, double valor2) {
       return valor1 - valor2;
    }

    @Override
    public double div(double valor1, double valor2) {
        if (valor2 != 0) {
            return valor1 / valor2;
        }
        return Double.MAX_VALUE;
    }

    @Override
    public double mult(double valor1, double valor2) {
       return valor1 * valor2;
    }
    
    
    
}
