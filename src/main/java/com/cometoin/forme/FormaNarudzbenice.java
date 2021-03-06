/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormaNarudzbenice.java
 *
 * Created on Jun 3, 2010, 6:36:13 PM
 */
package com.cometoin.forme;

import java.util.LinkedList;
import java.util.List;

import javax.swing.ListSelectionModel;

import com.cometoin.forme.komponente.NarudzbeniceTableModel;
import com.cometoin.kontrolerKorisnickogInterfejsa.KontrolerFormeNarudzbenice;

/**
 *
 * @author user
 */
public class FormaNarudzbenice extends javax.swing.JDialog {

    KontrolerFormeNarudzbenice kfn;
    List listaNarudzbenica;

    /** Creates new form FormaNarudzbenice */
    public FormaNarudzbenice(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        kfn = new KontrolerFormeNarudzbenice();
        listaNarudzbenica = kfn.vratiSveNarudzbenice();
        jtNarudzbenice.setModel(new NarudzbeniceTableModel(listaNarudzbenica));
        jtNarudzbenice.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setTitle("Narudzbenice");
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
        jtNarudzbenice = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jbObrisiNarudzbenicu = new javax.swing.JButton();
        jbDodajNovuNarudzbenicu = new javax.swing.JButton();
        jbIzmeniNarudzbenicu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtNarudzbenice.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtNarudzbenice);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jbObrisiNarudzbenicu.setText("Obrisi narudzbenicu");
        jbObrisiNarudzbenicu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbObrisiNarudzbenicuActionPerformed(evt);
            }
        });

        jbDodajNovuNarudzbenicu.setText("Dodaj novu narudzbenicu");
        jbDodajNovuNarudzbenicu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDodajNovuNarudzbenicuActionPerformed(evt);
            }
        });

        jbIzmeniNarudzbenicu.setText("izmeni narudzbenicu");
        jbIzmeniNarudzbenicu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbIzmeniNarudzbenicuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(324, Short.MAX_VALUE)
                .addComponent(jbDodajNovuNarudzbenicu)
                .addGap(18, 18, 18)
                .addComponent(jbObrisiNarudzbenicu)
                .addGap(18, 18, 18)
                .addComponent(jbIzmeniNarudzbenicu)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbDodajNovuNarudzbenicu)
                    .addComponent(jbObrisiNarudzbenicu)
                    .addComponent(jbIzmeniNarudzbenicu))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-829)/2, (screenSize.height-513)/2, 829, 513);
    }// </editor-fold>//GEN-END:initComponents

    private void jbDodajNovuNarudzbenicuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDodajNovuNarudzbenicuActionPerformed
        kfn.dodajNovuNarudzbenicu(jtNarudzbenice, listaNarudzbenica);
    }//GEN-LAST:event_jbDodajNovuNarudzbenicuActionPerformed

    private void jbIzmeniNarudzbenicuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbIzmeniNarudzbenicuActionPerformed
        kfn.izmeniNarudzbenicu(jtNarudzbenice, listaNarudzbenica);
    }//GEN-LAST:event_jbIzmeniNarudzbenicuActionPerformed

    private void jbObrisiNarudzbenicuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbObrisiNarudzbenicuActionPerformed
        kfn.obrisiNarudzbenicu(jtNarudzbenice, listaNarudzbenica);
    }//GEN-LAST:event_jbObrisiNarudzbenicuActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbDodajNovuNarudzbenicu;
    private javax.swing.JButton jbIzmeniNarudzbenicu;
    private javax.swing.JButton jbObrisiNarudzbenicu;
    private javax.swing.JTable jtNarudzbenice;
    // End of variables declaration//GEN-END:variables
}
