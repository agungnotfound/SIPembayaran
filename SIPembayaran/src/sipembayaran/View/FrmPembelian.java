package sipembayaran.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sipembayaran.Controller.DetailPembelianJpaController;
import sipembayaran.Controller.PembelianJpaController;
import sipembayaran.Model.DetailPembelian;
import sipembayaran.Model.Pembelian;
import static sipembayaran.SIPembayaran.EMF;
import static sipembayaran.View.FrmPembayaran.customformat;

/**
 *
 * @author agungnotfound
 */
public class FrmPembelian extends javax.swing.JInternalFrame {

    Connection con;
    ResultSet rs;
    Statement st;

    Pembelian pbl;
    DetailPembelian dtlPbl;
    DetailPembelianJpaController dtlCtrl = new DetailPembelianJpaController(EMF);
    PembelianJpaController pblCtrl = new PembelianJpaController(EMF);

    /**
     * Creates new form FrmPembayaran
     */
    public FrmPembelian() {
        initComponents();
        reset();
        nofaktur();
        txtSupplier.setFocusable(true);
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
        detailPembelianQuery = java.beans.Beans.isDesignTime() ? null : SIPembayaranPUEntityManager.createQuery("SELECT d FROM DetailPembelian d");
        detailPembelianList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : detailPembelianQuery.getResultList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNoTrans = new javax.swing.JTextField();
        txtSupplier = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dcTanggal = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetail = new javax.swing.JTable();

        setClosable(true);

        jLabel1.setText("No. Transaksi");

        jLabel2.setText("Supplier");

        jLabel3.setText("Tanggal");

        txtNoTrans.setEditable(false);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnPrint.setText("Print");

        lblTotal.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblTotal.setText("0");

        jLabel5.setText("Rp.");

        tblDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Nama Barang", "Harga", "Jumlah", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDetail.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tblDetailMouseMoved(evt);
            }
        });
        tblDetail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblDetailKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblDetail);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSupplier, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dcTanggal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNoTrans))
                        .addGap(51, 51, 51)
                        .addComponent(btnSimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCancel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTotal))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
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
                            .addComponent(txtSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(btnSimpan)
                        .addComponent(btnCancel)
                        .addComponent(btnPrint)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(dcTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(lblTotal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if (!isDataValid()) {
            JOptionPane.showMessageDialog(null, "Periksa Kembali");
        }

        boolean isNew = pbl == null;

        if (isNew) {
            pbl = new Pembelian();
        }

        pbl.setIdPembelian(txtNoTrans.getText());
        pbl.setSupplier(txtSupplier.getText());
        pbl.setTanggal(dcTanggal.getDate());

        if (isNew) {
            try {
                pblCtrl.create(pbl);
                JOptionPane.showMessageDialog(null, "Data Berhasil disimpan");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Data Gagal disimpan");
                Logger.getLogger(FrmPembelian.class.getName()).log(Level.SEVERE, null, ex);
            }

            DefaultTableModel model = (DefaultTableModel) tblDetail.getModel();
            List<DetailPembelian> listDetailPembelian = new ArrayList<DetailPembelian>();
            pbl = pblCtrl.findPembelian(txtNoTrans.getText());
            for (int i = 0; i < model.getRowCount(); i++) {
                DetailPembelian detail = new DetailPembelian();
                detail.setIdPembelian(pbl);
                detail.setNamaBarang((String) model.getValueAt(i, 0));
                detail.setHarga((Double) model.getValueAt(i, 1));
                detail.setJumlah((Double) model.getValueAt(i, 2));
                detail.setTotal((Double) model.getValueAt(i, 3));

                try {
                    dtlCtrl.create(detail);
                } catch (Exception e) {
                
                }
                
            }
            this.dispose();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tblDetailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDetailKeyReleased
        DefaultTableModel model = (DefaultTableModel) tblDetail.getModel();
        Vector row = new Vector();
        if (evt.isShiftDown()) {
            model.addRow(row);
        }
    }//GEN-LAST:event_tblDetailKeyReleased

    private void tblDetailMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetailMouseMoved
        hitung();
    }//GEN-LAST:event_tblDetailMouseMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager SIPembayaranPUEntityManager;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSimpan;
    private com.toedter.calendar.JDateChooser dcTanggal;
    private java.util.List<sipembayaran.Model.DetailPembelian> detailPembelianList;
    private javax.persistence.Query detailPembelianQuery;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblDetail;
    private javax.swing.JTextField txtNoTrans;
    private javax.swing.JTextField txtSupplier;
    // End of variables declaration//GEN-END:variables

    private boolean isDataValid() {
        if (txtNoTrans.getText().equals("")) {
            return false;
        } else if (txtSupplier.getText().equals("")) {
            return false;
        } else if (dcTanggal.getDate().equals(null)) {
            return false;

//        }else if(){    
        } else {
            return true;
        }
    }

    public void reset() {
//        txtNoTrans.setText("");
        txtSupplier.setText("");
        dcTanggal.setDate(null);
        tblDetail.updateUI();
        detailPembelianList.clear();
    }

    public void hitung() {
        double grandtotal = 0;
        try {
            for (int i = 0; i < tblDetail.getRowCount(); i++) {
                double total = 0;
                total = Double.parseDouble(tblDetail.getValueAt(i, 1) + "")
                        * Double.parseDouble(tblDetail.getValueAt(i, 2) + "");
                tblDetail.setValueAt(total, i, 3);
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
            System.out.println("koneksi berhasil;");
        } catch (Exception e) {
            System.err.println("koneksi gagal" + e.getMessage());
        }
    }

    private void nofaktur() {
        koneksi();
        String sql = "SELECT right(id_pembelian,2) as kd FROM pembelian";

        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);
            if (rs.first() == false) {
                txtNoTrans.setText("PB0001");
            } else {

                rs.last();
                int no = rs.getInt(1) + 1;
                String cno = String.valueOf(no);
                int pjg_cno = cno.length();
                for (int i = 0; i < 2 - pjg_cno; i++) {
                    cno = "000" + cno;
                }
                txtNoTrans.setText("PB" + cno);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi Database Gagal ! Periksa Database atau Hubungi Penyedia");
        }

    }

}