package sipembayaran.View;

import java.awt.Dimension;
import javax.swing.JInternalFrame;
import static sipembayaran.SIPembayaran.USER;

/**
 *
 * @author agungnotfound
 */
public class Home extends javax.swing.JFrame {
    
    FrmUser Fuser;
    FrmMenu Fmenu;
    FrmDataPembayaran bayar;
    FrmDataPembelian beli;
    FrmPembayaran fbayar;
    FrmReport frmreport;

    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        
        if (USER != null) {
            this.setTitle("Your Login as : " + USER.getUsername());
        }
        
        if (USER != null) {
            switch (USER.getLevel()){
                case "owner":
                    break;
                case "cashier":
                    miUser.setVisible(false);
                    menuReport.setVisible(false);
                    break;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dekstopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        miBahanBaku = new javax.swing.JMenu();
        miMenu = new javax.swing.JMenuItem();
        miUser = new javax.swing.JMenuItem();
        miExit = new javax.swing.JMenuItem();
        muPenjualan = new javax.swing.JMenu();
        miPembelian = new javax.swing.JMenuItem();
        miPenjualan = new javax.swing.JMenuItem();
        menuReport = new javax.swing.JMenu();
        miRSales = new javax.swing.JMenuItem();
        miRPurchase = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        miBahanBaku.setText("Master");

        miMenu.setText("Data Menu");
        miMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miMenuActionPerformed(evt);
            }
        });
        miBahanBaku.add(miMenu);

        miUser.setText("User");
        miUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miUserActionPerformed(evt);
            }
        });
        miBahanBaku.add(miUser);

        miExit.setText("Exit");
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });
        miBahanBaku.add(miExit);

        jMenuBar1.add(miBahanBaku);

        muPenjualan.setText("Transaksi");

        miPembelian.setText("Pembelian");
        miPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPembelianActionPerformed(evt);
            }
        });
        muPenjualan.add(miPembelian);

        miPenjualan.setText("Penjualan");
        miPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPenjualanActionPerformed(evt);
            }
        });
        muPenjualan.add(miPenjualan);

        jMenuBar1.add(muPenjualan);

        menuReport.setText("Laporan");

        miRSales.setText("Sales Report");
        miRSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRSalesActionPerformed(evt);
            }
        });
        menuReport.add(miRSales);

        miRPurchase.setText("Purchase Report ");
        menuReport.add(miRPurchase);

        jMenuBar1.add(menuReport);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(dekstopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dekstopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPenjualanActionPerformed
        if (fbayar == null) {
            fbayar = new FrmPembayaran();
        }
        showForm(fbayar);
        makeCenter(fbayar);
    }//GEN-LAST:event_miPenjualanActionPerformed

    private void miPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPembelianActionPerformed
        if (beli == null) {
            beli = new FrmDataPembelian();
        }
        showForm(beli);
        makeCenter(beli);
    }//GEN-LAST:event_miPembelianActionPerformed

    private void miRSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRSalesActionPerformed
        if (bayar == null) {
            bayar = new FrmDataPembayaran();
        }
        showForm(bayar);
        makeCenter(bayar);
    }//GEN-LAST:event_miRSalesActionPerformed

    private void miExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_miExitActionPerformed

    private void miUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miUserActionPerformed
        if (Fuser == null) {
            Fuser = new FrmUser();
        }
        showForm(Fuser);
        makeCenter(Fuser);
    }//GEN-LAST:event_miUserActionPerformed

    private void miMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miMenuActionPerformed
        if (Fmenu == null) {
            Fmenu = new FrmMenu();
        }
        showForm(Fmenu);
        makeCenter(Fmenu);
    }//GEN-LAST:event_miMenuActionPerformed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane dekstopPane;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuReport;
    private javax.swing.JMenu miBahanBaku;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenuItem miMenu;
    private javax.swing.JMenuItem miPembelian;
    private javax.swing.JMenuItem miPenjualan;
    private javax.swing.JMenuItem miRPurchase;
    private javax.swing.JMenuItem miRSales;
    private javax.swing.JMenuItem miUser;
    private javax.swing.JMenu muPenjualan;
    // End of variables declaration//GEN-END:variables

        private void showForm(Object obj){
        JInternalFrame jf = null;
        if(obj  instanceof JInternalFrame){
            jf = (JInternalFrame) obj;
            if (!jf.isVisible()){
                dekstopPane.add(jf);
            }           
        
        jf.setVisible(true);
            try {
//                jf.setMaximum(true);
                jf.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
             e.printStackTrace();
            }
        }
    }
    
        private void makeCenter(JInternalFrame internalFrame){
        Dimension desktopSize = dekstopPane.getSize();
        Dimension jInternalFrameSize = internalFrame.getSize();
        internalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
            (desktopSize.height- jInternalFrameSize.height)/2);
    }

}
