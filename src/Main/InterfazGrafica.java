/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Graficos.Lienzo;
import Logic.Barberia;
import Logic.Cliente;
import Logic.MessageConsole;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author Jorge
 */
public class InterfazGrafica extends javax.swing.JFrame {

    /**
     * Creates new form InterfazGrafica
     */
    Lienzo lienzo;
    int customerCounter = 0;

    public void setLienzo(Lienzo lienzo) {
        this.lienzo = lienzo;
    }

    public InterfazGrafica() {
        initComponents();

    }

    public void inicializarBotonCliente() {
        botonCliente.setBounds(this.getWidth() / 3 - 75, this.getHeight() - 125, 150, 70);

    }

    public void inicializarBotonPausa() {
        botonPausa.setBounds(this.getWidth() - this.getWidth() / 3 - 75, this.getHeight() - 125, 150, 70);
    }
    public void inicializarBotonClienteIdos() {
        botonClientesIdos.setBounds(this.getWidth() - this.getWidth() / 2 - 75, this.getHeight() - 125, 150, 70);
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        botonCliente = new javax.swing.JButton();
        botonPausa = new javax.swing.JButton();
        botonClientesIdos = new javax.swing.JButton();
        consoleLog = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        botonCliente.setText("Nuevo Cliente");
        botonCliente.setMaximumSize(new java.awt.Dimension(10000, 10000));
        botonCliente.setMinimumSize(new java.awt.Dimension(0, 0));
        botonCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClienteActionPerformed(evt);
            }
        });
        getContentPane().add(botonCliente);
        botonCliente.setBounds(370, 350, 99, 33);

        botonPausa.setText("Pausar");
        botonPausa.setMaximumSize(new java.awt.Dimension(10000, 10000));
        botonPausa.setMinimumSize(new java.awt.Dimension(0, 0));
        botonPausa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPausaActionPerformed(evt);
            }
        });
        getContentPane().add(botonPausa);
        botonPausa.setBounds(660, 350, 65, 33);

        botonClientesIdos.setText("Clientes Idos");
        botonClientesIdos.setMaximumSize(new java.awt.Dimension(10000, 10000));
        botonClientesIdos.setMinimumSize(new java.awt.Dimension(0, 0));
        botonClientesIdos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(null, "Se han ido "+Cliente.clientesIdos+ " clientes.");
                
            }
        });
        getContentPane().add(botonClientesIdos);
        botonClientesIdos.setBounds(450, 600, 65, 33);
        
        jScrollPane1.setViewportView(consoleLog);
        jScrollPane1.setEnabled(false);
        consoleLog.setEditable(false);
        consoleLog.setBackground(Color.black);
        getContentPane().add(jScrollPane1);
        MessageConsole mc = new MessageConsole(consoleLog);
        mc.redirectOut(Color.white, null);
        jScrollPane1.setBounds(1000, 50, 330, 550);

        jLabel1.setText("Registro");
        jLabel1.setBounds(1000 + 165 - 40, 10, 80, 25);

        getContentPane().add(jLabel1);

        pack();
    }// </editor-fold>

   private void botonClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonClienteActionPerformed

       customerCounter++;
       Cliente cliente = new Cliente(Main.barberia, "Cliente " + customerCounter);

       cliente.getCurrentAnimation().setLocation(lienzo.getWidth() / 2 - 17, lienzo.getHeight() - 50);
       cliente.label.setBounds(cliente.getCurrentAnimation().x+16, cliente.getCurrentAnimation().y-50, 75, 10);
       cliente.label.setForeground(Color.GREEN);
       lienzo.add(cliente.label);
        //cliente.tiempoInicial = System.currentTimeMillis();
       cliente.start();
       //cliente.getCurrentAnimation().start();
       
       
       lienzo.clientes.add(cliente);

    }//GEN-LAST:event_botonClienteActionPerformed

    synchronized private void detenerHilos() {
        lienzo.barber.suspend();
        for (Cliente cliente : lienzo.clientes) {
            cliente.suspend();
        }
    }
    private void botonPausaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPausaActionPerformed
        if (!lienzo.paused) {
            lienzo.paused = true;

            /*
            Cliente.haSidoPausado = true;
            Barbero.haSidoPausado = true;
             */
            detenerHilos();

            Barberia.Log("Barbería pausada....");
            Main.tiempoInicioPausa = System.currentTimeMillis();
            botonPausa.setText("Reanudar");

        } else {
            lienzo.paused = false;
            Main.tiempoFinalPausa = System.currentTimeMillis();
            synchronized (lienzo.barber) {
                lienzo.barber.resume();
            }

            for (Cliente cliente : lienzo.clientes) {
                synchronized (cliente) {
                    cliente.resume();
                }
            }
            Barberia.Log("Pausa terminada.");
            botonPausa.setText("Pausar");
        }
    }//GEN-LAST:event_botonPausaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
 /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazGrafica().setVisible(true);
            }
        });
         */
    }

    // Variables declaration - do not modify    
    private javax.swing.JButton botonCliente;
    private javax.swing.JButton botonPausa;
    private javax.swing.JButton botonClientesIdos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane consoleLog;
    // End of variables declaration                   
    private javax.swing.JLabel jLabel1;
}
