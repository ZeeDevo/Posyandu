/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author untuk
 */
public class PanelLaporan extends javax.swing.JPanel {

    /**
     * Creates new form PanelLaporan
     */
    public PanelLaporan() {
        initComponents();
        cmbJenisLaporan.removeAllItems();
        cmbJenisLaporan.addItem("Data Balita");
        cmbJenisLaporan.addItem("Data Ibu Hamil");
        cmbJenisLaporan.addItem("Data Kunjungan");
        cmbJenisLaporan.addItem("Data Kegiatan");
        
        btnTampilkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilkanActionPerformed(evt);
            }
        });
    }
   
    
    private void tampilBalita() {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("NIK");
        model.addColumn("Nama");
        model.addColumn("Tanggal Lahir");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Nama Orang Tua");
        model.addColumn("Alamat");
        model.addColumn("No KIA");

        try {

            Connection conn = Uas.getKoneksi();

            String sql = "SELECT nik,nama,tgl_lahir,jenis_kelamin,nama_ortu,alamat,no_kia FROM balita";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{
                    rs.getString("nik"),
                    rs.getString("nama"),
                    rs.getDate("tgl_lahir"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("nama_ortu"),
                    rs.getString("alamat"),
                    rs.getString("no_kia")
                });

            }

            tblLaporan.setModel(model);

            rs.close();
            ps.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,
                    "Gagal menampilkan data balita\n" + e.getMessage());

        }

    }
    
    private void tampilIbuHamil() {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("NIK");
        model.addColumn("Nama");
        model.addColumn("HPHT");
        model.addColumn("Usia Kehamilan");
        model.addColumn("Risiko");
        model.addColumn("Alamat");

        try {

            Connection conn = Uas.getKoneksi();

            String sql = "SELECT * FROM ibu_hamil";

            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{
                    rs.getString("nik"),
                    rs.getString("nama"),
                    rs.getString("hpht"),
                    rs.getInt("usia_kehamilan"),
                    rs.getString("risiko"),
                    rs.getString("alamat")
                });

            }

            tblLaporan.setModel(model);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }
    
    private void tampilKunjungan() {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Tanggal");
        model.addColumn("NIK");
        model.addColumn("Nama");
        model.addColumn("Orang Tua");
        model.addColumn("Alamat");

        try {

            Connection conn = Uas.getKoneksi();

            String sql = "SELECT * FROM kunjungan";

            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{
                    rs.getString("tanggal_kunjungan"),
                    rs.getString("nik"),
                    rs.getString("nama"),
                    rs.getString("nama_ortu"),
                    rs.getString("alamat")
                });

            }

            tblLaporan.setModel(model);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }
    
    private void tampilKegiatan() {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Tanggal");
        model.addColumn("Nama");
        model.addColumn("BB");
        model.addColumn("TB");
        model.addColumn("Usia");
        model.addColumn("Status Gizi");

        try {

            Connection conn = Uas.getKoneksi();

            String sql =
            "SELECT k.tanggal,b.nama,k.berat_badan,k.tinggi_badan,k.usia,k.status_gizi " +
            "FROM kegiatan k JOIN balita b ON k.id_balita=b.id_balita";

            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{
                    rs.getString("tanggal"),
                    rs.getString("nama"),
                    rs.getDouble("berat_badan"),
                    rs.getDouble("tinggi_badan"),
                    rs.getInt("usia"),
                    rs.getString("status_gizi")
                });

            }

            tblLaporan.setModel(model);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

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

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cmbJenisLaporan = new javax.swing.JComboBox<>();
        btnTampilkan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLaporan = new javax.swing.JTable();
        btnEkspor = new javax.swing.JButton();
        txtDariTanggal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSampaiTanggal = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(17, 24, 68));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Laporan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Jenis laporan");

        cmbJenisLaporan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbJenisLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJenisLaporanActionPerformed(evt);
            }
        });

        btnTampilkan.setText("Tampilkan");
        btnTampilkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilkanActionPerformed(evt);
            }
        });

        tblLaporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblLaporan);

        btnEkspor.setText("Ekspor  PDF");
        btnEkspor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEksporActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Dari Tanggal");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Sampai Tanggal");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbJenisLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(106, 106, 106)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtDariTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtSampaiTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTampilkan)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEkspor)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbJenisLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEkspor)
                            .addComponent(txtDariTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSampaiTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTampilkan))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbJenisLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJenisLaporanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbJenisLaporanActionPerformed

    private void btnEksporActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEksporActionPerformed
        if(tblLaporan.getRowCount()==0){
            JOptionPane.showMessageDialog(this,"Data masih kosong.");
            return;
        }

        JFileChooser chooser = new JFileChooser();

        chooser.setSelectedFile(new java.io.File("Laporan.pdf"));

        int hasil = chooser.showSaveDialog(this);

        if(hasil==JFileChooser.APPROVE_OPTION){

            try{

                String path = chooser.getSelectedFile().getAbsolutePath();

                if(!path.toLowerCase().endsWith(".pdf")){
                    path += ".pdf";
                }

                Document document = new Document();

                PdfWriter.getInstance(document,new FileOutputStream(path));

                document.open();

                Paragraph judul = new Paragraph(
                        "LAPORAN POSYANDU\n\n",
                        FontFactory.getFont(FontFactory.HELVETICA_BOLD,16)
                );

                judul.setAlignment(Element.ALIGN_CENTER);

                document.add(judul);

                document.add(new Paragraph(
                        "Jenis Laporan : "
                        +cmbJenisLaporan.getSelectedItem().toString()));

                document.add(new Paragraph(
                        "Periode : "
                        +txtDariTanggal.getText()
                        +" s/d "
                        +txtSampaiTanggal.getText()));

                document.add(new Paragraph(" "));

                TableModel model = tblLaporan.getModel();

                PdfPTable table =
                        new PdfPTable(model.getColumnCount());

                table.setWidthPercentage(100);

                // Header
                for(int i=0;i<model.getColumnCount();i++){

                    PdfPCell cell =
                            new PdfPCell(
                                    new Phrase(model.getColumnName(i)));

                    table.addCell(cell);

                }

                // Isi
                for(int i=0;i<model.getRowCount();i++){

                    for(int j=0;j<model.getColumnCount();j++){

                        Object obj=model.getValueAt(i,j);

                        table.addCell(
                                obj==null?"":obj.toString());

                    }

                }

                document.add(table);

                document.close();

                JOptionPane.showMessageDialog(this,
                        "PDF berhasil disimpan.");

            }catch(Exception e){

                JOptionPane.showMessageDialog(this,e.getMessage());

            }

        }

// TODO add your handling code here:
    }//GEN-LAST:event_btnEksporActionPerformed

    private void btnTampilkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilkanActionPerformed

        String pilih = cmbJenisLaporan.getSelectedItem().toString();

        switch (pilih) {

            case "Data Balita":
                tampilBalita();
                break;

            case "Data Ibu Hamil":
                tampilIbuHamil();
                break;

            case "Data Kunjungan":
                tampilKunjungan();
                break;

            case "Data Kegiatan":
                tampilKegiatan();
                break;
        }

// TODO add your handling code here:
    }//GEN-LAST:event_btnTampilkanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEkspor;
    private javax.swing.JButton btnTampilkan;
    private javax.swing.JComboBox<String> cmbJenisLaporan;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLaporan;
    private javax.swing.JTextField txtDariTanggal;
    private javax.swing.JTextField txtSampaiTanggal;
    // End of variables declaration//GEN-END:variables
}
