package Main;

import Graficos.Lienzo;
import Logic.Barberia;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mzapataj
 */
public class Main {

    public static Barberia barberia;
    public static long tiempoInicioPausa;
    public static long tiempoFinalPausa;
    
    public static void main(String[] args) throws InterruptedException {

        

        int numSillas;
        do{
            try {
                numSillas = Integer.valueOf(JOptionPane.showInputDialog(null, "Digite el numero de sillas de la barbería (máx 12 sillas)", "Barbero Dormilón", 1));
            } catch (NumberFormatException e) {
                numSillas = -1;
            }
        }while(numSillas > 12);
        
        if (numSillas != -1) {
            
            InterfazGrafica ui = new InterfazGrafica();
            ui.setExtendedState(JFrame.MAXIMIZED_BOTH);
            ui.setVisible(true);

            barberia = new Barberia(numSillas);

            Lienzo lienzo = new Lienzo(ui.getWidth(), ui.getHeight(), barberia);
            barberia.setLienzo(lienzo);
            lienzo.barber.start();
            
            ui.inicializarBotonCliente();
            ui.inicializarBotonPausa();
            ui.inicializarBotonClienteIdos();
            
            ui.setLienzo(lienzo);
            ui.add(lienzo);

            lienzo.start();
        }
    }
}
