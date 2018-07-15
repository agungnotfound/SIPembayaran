package sipembayaran.View;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import sipembayaran.Controller.DetailPenjualanJpaController;
import sipembayaran.Controller.MenuJpaController;
import sipembayaran.Controller.PenjualanJpaController;
import sipembayaran.Model.DetailPenjualan;
import sipembayaran.Model.Menu;
import sipembayaran.Model.Penjualan;
import static sipembayaran.SIPembayaran.EMF;

/**
 *
 * @author agungnotfound
 */
public class FrmPembayaran extends javax.swing.JInternalFrame {

    Connection con;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;
    String sql;

    FrmDataPembayaran fDataBayar;

    Menu menu;
    Penjualan pjl;
    DetailPenjualan dtPjl;
    MenuJpaController menuCtrl = new MenuJpaController(EMF);
    PenjualanJpaController pjlCtrl = new PenjualanJpaController(EMF);
    DetailPenjualanJpaController dtCtrl = new DetailPenjualanJpaController(EMF);

    DefaultTableModel model;

    /**
     * Creates new form FrmPembayaran
     */
    public FrmPembayaran() {
        initComponents();
        nofaktur();
    }

    public FrmPembayaran(FrmDataPembayaran fDataBayar) {
        initComponents();
//        nofaktur();
        this.fDataBayar = fDataBayar;
        edit();

    }

    FrmPembayaran(Penjualan pjl, DetailPenjualan dpjl, FrmDataPembayaran fDataBayar) {
        initComponents();
//        nofaktur();
        this.fDataBayar = fDataBayar;
        this.pjl = pjl;
        this.dtPjl = dpjl;
        edit();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SIPembayaranPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("SIPembayaranPU").createEntityManager();
        penjualanQuery = java.beans.Beans.isDesignTime() ? null : SIPembayaranPUEntityManager.createQuery("SELECT p FROM Penjualan p");
        penjualanList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : penjualanQuery.getResultList();
        cmbMenu = new javax.swing.JComboBox<>();
        detailPenjualanQuery = java.beans.Beans.isDesignTime() ? null : SIPembayaranPUEntityManager.createQuery("SELECT d FROM DetailPenjualan d");
        detailPenjualanList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(detailPenjualanQuery.getResultList());
        jLabel1 = new javax.swing.JLabel();
        txtNoMeja = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetail = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNoTrans = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        cmbMenu.removeAllItems();
        for (Menu menu : menuCtrl.findMenuEntities()){
            cmbMenu.addItem(menu.getIdMenu());
        }
        cmbMenu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMenuItemStateChanged(evt);
            }
        });

        setClosable(true);
        setResizable(true);

        jLabel1.setText("No. Transaksi");

        txtNoMeja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoMejaActionPerformed(evt);
            }
        });

        tblDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Menu", "Menu", "Harga", "jumlah", "Sub Total"
            }
        ));
        tblDetail.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tblDetailMouseMoved(evt);
            }
        });
        tblDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetailMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblDetailMouseEntered(evt);
            }
        });
        tblDetail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblDetailKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetail);
        if (tblDetail.getColumnModel().getColumnCount() > 0) {
            tblDetail.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cmbMenu));
        }

        jLabel2.setText("Nama");

        jLabel3.setText("No. Meja");

        txtNoTrans.setEditable(false);

        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        jButton2.setText("Cetak");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        lblTotal.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblTotal.setText("0");

        jLabel5.setText("Rp.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNoTrans, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(txtNama))
                                .addGap(66, 66, 66)
                                .addComponent(btnSimpan))
                            .addComponent(txtNoMeja, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNoTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(btnSimpan)
                        .addComponent(jButton2)
                        .addComponent(btnClose)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNoMeja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTotal)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if (!isDataValid()) {
            JOptionPane.showMessageDialog(null, "Nama dan Meja tidak boleh Kosong!");
        } else {
            boolean isNew = pjl == null;

            if (isNew) {
                pjl = new Penjualan();
            }

            pjl.setIdPenjualan(txtNoTrans.getText());
            pjl.setNama(txtNama.getText());
            pjl.setNoMeja(Integer.parseInt(txtNoMeja.getText()));
            Date date = new Date();
            pjl.setTanggal(date);

            if (isNew) {
                try {
                    pjlCtrl.create(pjl);
                    JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Data Gagal disimpan!");
                    Logger.getLogger(FrmPembayaran.class.getName()).log(Level.SEVERE, null, ex);
                }

                //coding at table
                DefaultTableModel model = (DefaultTableModel) tblDetail.getModel();
                List<DetailPenjualan> listDetailPenjualan = new ArrayList<DetailPenjualan>();
                pjl = pjlCtrl.findPenjualan(txtNoTrans.getText());
                menu = menuCtrl.findMenu(cmbMenu.getSelectedItem().toString());
                for (int i = 0; i < model.getRowCount(); i++) {
                    DetailPenjualan detail = new DetailPenjualan();
                    detail.setIdPenjualan(pjl);
                    detail.setIdMenu(menu);
                    detail.setNama((String) model.getValueAt(i, 1));
                    detail.setHarga((Double) model.getValueAt(i, 2));
                    detail.setJumlah((Integer) model.getValueAt(i, 3));
                    detail.setTotal((Double) model.getValueAt(i, 4));

                    try {
                        dtCtrl.create(detail);

                    } catch (Exception e) {
                    }
                }
                reset();
//                this.dispose();
            }

        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void txtNoMejaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoMejaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoMejaActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        reset();
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void cmbMenuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMenuItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            Menu mn = menuCtrl.findMenu(cmbMenu.getSelectedItem().toString());
            if (mn != null) {
                int selectedRow = tblDetail.getSelectedRow();
                tblDetail.setValueAt(mn.getNama(), selectedRow, 1);
                tblDetail.setValueAt(mn.getHarga(), selectedRow, 2);
            }
        }
    }//GEN-LAST:event_cmbMenuItemStateChanged

    private void tblDetailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDetailKeyReleased
        DefaultTableModel model = (DefaultTableModel) tblDetail.getModel();
        Vector row = new Vector();
        if (evt.isShiftDown()) {
            model.addRow(row);
        }
    }//GEN-LAST:event_tblDetailKeyReleased

    private void tblDetailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetailMouseEntered

    }//GEN-LAST:event_tblDetailMouseEntered

    private void tblDetailMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetailMouseMoved

    }//GEN-LAST:event_tblDetailMouseMoved

    private void tblDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetailMouseClicked
        hitung();
    }//GEN-LAST:event_tblDetailMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        btnSimpanActionPerformed(evt);
        EntityManager em = null;
        try {
            em = EMF.createEntityManager();
            em.getTransaction().begin();
            Connection connect = em.unwrap(Connection.class);
            File file = new File("");
            String sourcefilename = file.getAbsolutePath() + "\\src\\sipembayaran\\view\\Report\\Bill.jasper";
            HashMap parameter = new HashMap();
            parameter.put("idPenjualan", txtNoTrans.getText());

            JasperPrint jp = JasperFillManager.fillReport(sourcefilename, parameter, connect);
            //untuk buka report
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
            //untuk buka report

//            JRViewer viewer = new JRViewer(jp);
//            viewer.setOpaque(true);
//            viewer.setVisible(true);
//            splLaporan.add(viewer);
//            splLaporan.setViewportView(viewer);
            em.getTransaction().commit();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Tidak bisa Print:" + e.getMessage());

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager SIPembayaranPUEntityManager;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cmbMenu;
    private java.util.List<sipembayaran.Model.DetailPenjualan> detailPenjualanList;
    private javax.persistence.Query detailPenjualanQuery;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private java.util.List<sipembayaran.Model.Penjualan> penjualanList;
    private javax.persistence.Query penjualanQuery;
    private javax.swing.JTable tblDetail;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoMeja;
    private javax.swing.JTextField txtNoTrans;
    // End of variables declaration//GEN-END:variables

    private boolean isDataValid() {
        if (txtNama.getText().equals("")) {
            return false;
        } else if (txtNoMeja.getText().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public void reset() {
        txtNoTrans.setText("");
        txtNama.setText("");
        txtNoMeja.setText("");
        cmbMenu.setSelectedItem(0);
        tblDetail.updateUI();
        penjualanList.clear();
    }

    public void edit() {
        if (pjl != null) {
            txtNoTrans.setText(pjl.getIdPenjualan());
            txtNama.setText(pjl.getNama());
            txtNoMeja.setText(pjl.getNoMeja().toString());
            lblTotal.setText("test");
            cmbMenu.setVisible(false);
            
//            int i = 0;
//                Object[] rowData ={"Kode Menu","Menu","Harga","Jumlah","Subtotal"};
//                int j = tblDetail.getModel().getRowCount();
//                
//                if(j>0){
//                    for (int k = j-1; k >= 0; k--) {
//                       ( (DefaultTableModel)tblDetail.getModel()).removeRow(k);
//                    }
//                }
            try {
                ps = con.prepareStatement("SELECT * FROM detail_penjualan WHERE id_penjualan = ?");
            } catch (Exception e) {
            }
            
            

        }
    }

    public static String customformat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        return myFormatter.format(value);
    }

    public void hitung() {
        double grandtotal = 0;
        try {
            for (int i = 0; i < tblDetail.getRowCount(); i++) {
                double total = 0;
                total = Double.parseDouble(tblDetail.getValueAt(i, 2) + "")
                        * Double.parseDouble(tblDetail.getValueAt(i, 3) + "");
                tblDetail.setValueAt(total, i, 4);
                grandtotal += total;
            }

        } catch (Exception e) {
        }
        lblTotal.setText(customformat("###,###", grandtotal));

        //koding hitung dalam table
    }

    public void koneksi() {
        try {
            String url = "jdbc:mysql://localhost/penjualan_db";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            st = con.createStatement();
//            System.out.println("koneksi berhasil;");
        } catch (Exception e) {
//            System.err.println("koneksi gagal" + e.getMessage());
        }
    }


    private void nofaktur() {
        koneksi();
        String sql = "select right(id_penjualan,2) as kd from penjualan";

        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);
            if (rs.first() == false) {
                txtNoTrans.setText("PJ0001");
            } else {

                rs.last();
                int no = rs.getInt(1) + 1;
                String cno = String.valueOf(no);
                int pjg_cno = cno.length();
                for (int i = 0; i < 2 - pjg_cno; i++) {
                    cno = "000" + cno;
                }
                txtNoTrans.setText("PJ" + cno);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi Database Gagal ! Periksa Database atau Hubungi Penyedia");
        }

    }

}
