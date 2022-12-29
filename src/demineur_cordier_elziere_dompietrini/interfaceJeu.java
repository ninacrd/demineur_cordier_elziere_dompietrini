/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package demineur_cordier_elziere_dompietrini;

import javax.swing.JLabel;

/**
 *
 * @author Nina
 */
public class interfaceJeu extends javax.swing.JFrame {
    
    /**
     * Creates new form interfaceJeu
     */
    public interfaceJeu() {
        initComponents();
        
        //add(new PlateauDeJeu());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        message = new javax.swing.JLabel();
        plateau = new demineur_cordier_elziere_dompietrini.PlateauDeJeu();
        lancer = new javax.swing.JButton();
        nb_mines = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DEMINEUR");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        message.setText(" ");
        getContentPane().add(message, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 301, 65));

        plateau.setVisible(false);

        javax.swing.GroupLayout plateauLayout = new javax.swing.GroupLayout(plateau);
        plateau.setLayout(plateauLayout);
        plateauLayout.setHorizontalGroup(
            plateauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );
        plateauLayout.setVerticalGroup(
            plateauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 241, Short.MAX_VALUE)
        );

        getContentPane().add(plateau, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 470, -1));

        lancer.setText("LANCER");
        lancer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lancerActionPerformed(evt);
            }
        });
        getContentPane().add(lancer, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 100, 30));

        nb_mines.setModel(new javax.swing.SpinnerNumberModel(50, 10, 100, 1));
        nb_mines.setRequestFocusEnabled(false);
        getContentPane().add(nb_mines, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 60, 30));

        jLabel1.setText("Combien de mines voulez vous poser ?");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 220, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lancerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lancerActionPerformed
        // TODO add your handling code here:
        int nombre_mines = (Integer) nb_mines.getValue();
        plateau.setmessage(message);
        plateau.setnb_mines(nombre_mines);
        plateau.partie();
        plateau.setVisible(true);
        plateau.repaint();
        //lancer.setEnabled(false);
    }//GEN-LAST:event_lancerActionPerformed

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
            java.util.logging.Logger.getLogger(interfaceJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(interfaceJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(interfaceJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(interfaceJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfaceJeu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton lancer;
    private javax.swing.JLabel message;
    private javax.swing.JSpinner nb_mines;
    private demineur_cordier_elziere_dompietrini.PlateauDeJeu plateau;
    // End of variables declaration//GEN-END:variables
}
