/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormaRacuni.java
 *
 * Created on Jun 9, 2010, 7:46:24 PM
 */
package forme;

import forme.komponente.RacuniTableModel;
import java.util.LinkedList;
import javax.swing.ListSelectionModel;
import kontrolerKorisnickogInterfejsa.KontrolerFormeRacuni;

/**
 *
 * @author user
 */
public class FormaRacuni extends javax.swing.JDialog {

    KontrolerFormeRacuni kfr;
    LinkedList listaRacuna;

    /** Creates new form FormaRacuni */
    public FormaRacuni(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        kfr = new KontrolerFormeRacuni();
        listaRacuna = kfr.vratiSveRacune();
        jtRacuni.setModel(new RacuniTableModel(listaRacuna));
        jtRacuni.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtRacuni = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jbDodajNoviRacun = new javax.swing.JButton();
        jbObrisiRacun = new javax.swing.JButton();
        jbIzmeniRacun = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Racuni");

        jtRacuni.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtRacuni);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jbDodajNoviRacun.setText("Dodaj novi racun");
        jbDodajNoviRacun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDodajNoviRacunActionPerformed(evt);
            }
        });

        jbObrisiRacun.setText("Obrisi racun");
        jbObrisiRacun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbObrisiRacunActionPerformed(evt);
            }
        });

        jbIzmeniRacun.setText("izmeni racun");
        jbIzmeniRacun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbIzmeniRacunActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(511, Short.MAX_VALUE)
                .addComponent(jbDodajNoviRacun)
                .addGap(18, 18, 18)
                .addComponent(jbObrisiRacun)
                .addGap(18, 18, 18)
                .addComponent(jbIzmeniRacun))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbDodajNoviRacun)
                    .addComponent(jbObrisiRacun)
                    .addComponent(jbIzmeniRacun))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-888)/2, (screenSize.height-527)/2, 888, 527);
    }// </editor-fold>//GEN-END:initComponents

    private void jbDodajNoviRacunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDodajNoviRacunActionPerformed
        kfr.dodajNoviRacun(jtRacuni, listaRacuna);
}//GEN-LAST:event_jbDodajNoviRacunActionPerformed

    private void jbObrisiRacunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbObrisiRacunActionPerformed
        kfr.obrisiRacun(jtRacuni, listaRacuna);
}//GEN-LAST:event_jbObrisiRacunActionPerformed

    private void jbIzmeniRacunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbIzmeniRacunActionPerformed
        kfr.izmeniRacun(jtRacuni, listaRacuna);
}//GEN-LAST:event_jbIzmeniRacunActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbDodajNoviRacun;
    private javax.swing.JButton jbIzmeniRacun;
    private javax.swing.JButton jbObrisiRacun;
    private javax.swing.JTable jtRacuni;
    // End of variables declaration//GEN-END:variables
}
