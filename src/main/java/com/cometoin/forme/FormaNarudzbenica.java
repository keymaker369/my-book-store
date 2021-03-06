/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NarudzbenicaForma.java
 *
 * Created on May 23, 2010, 12:50:26 AM
 */
package com.cometoin.forme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.TableColumn;

import com.cometoin.domenskeKlase.Narudzbenica;
import com.cometoin.domenskeKlase.Proizvod;
import com.cometoin.domenskeKlase.StavkaNarudzbenice;
import com.cometoin.forme.komponente.StavkaTableModel;
import com.cometoin.kontrolerKorisnickogInterfejsa.KontrolerFormeNarudzbenica;

/**
 *
 * @author user
 */
public class FormaNarudzbenica extends javax.swing.JDialog implements ActionListener {

    KontrolerFormeNarudzbenica kfn;
    Narudzbenica narudzbenica;
    //1 - dodaj novu narudzbenicu; 2 - zapamti izmenjenu narudzbenicu
    int mod;

    /** Creates new form NarudzbenicaForma */
    public FormaNarudzbenica(java.awt.Frame parent, boolean modal, int mod, Narudzbenica narudzbenica) {
        super(parent, modal);
        initComponents();
        this.mod = mod;
        this.narudzbenica = narudzbenica;
        jtStavkeNarudzbenice.setModel(new StavkaTableModel());
        kfn = new KontrolerFormeNarudzbenica();
        jcbProizvodi = napraviJCBProizvodi();
        srediFormu(mod);
        this.setTitle("Narudzbenica");

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
        jtStavkeNarudzbenice = new javax.swing.JTable();
        jbDodajNovuStavku = new javax.swing.JButton();
        jbZapamti = new javax.swing.JButton();
        jbOdustani = new javax.swing.JButton();
        jbObrisiStavku = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfDatum = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jtfSifraNarudzbeniceKupca = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfKupac = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtStavkeNarudzbenice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtStavkeNarudzbenice.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jtStavkeNarudzbenicePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jtStavkeNarudzbenice);

        jbDodajNovuStavku.setText("Dodaj novu stavku");
        jbDodajNovuStavku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDodajNovuStavkuActionPerformed(evt);
            }
        });

        jbZapamti.setText("Zapamti");
        jbZapamti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbZapamtiActionPerformed(evt);
            }
        });

        jbOdustani.setText("Odustani");
        jbOdustani.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbOdustaniActionPerformed(evt);
            }
        });

        jbObrisiStavku.setText("Obrisi");
        jbObrisiStavku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbObrisiStavkuActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("Datum:");

        jLabel1.setText("Sifra narudzbenice kupca:");

        jLabel3.setText("Kupac:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfSifraNarudzbeniceKupca)
                    .addComponent(jtfKupac, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                    .addComponent(jtfDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfSifraNarudzbeniceKupca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfKupac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jbDodajNovuStavku)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbObrisiStavku)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 359, Short.MAX_VALUE)
                .addComponent(jbOdustani)
                .addGap(18, 18, 18)
                .addComponent(jbZapamti, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbZapamti)
                    .addComponent(jbOdustani)
                    .addComponent(jbDodajNovuStavku)
                    .addComponent(jbObrisiStavku))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-803)/2, (screenSize.height-551)/2, 803, 551);
    }// </editor-fold>//GEN-END:initComponents

    private void jbDodajNovuStavkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDodajNovuStavkuActionPerformed
        kfn.dodajStavku(jtStavkeNarudzbenice, jcbProizvodi);
    }//GEN-LAST:event_jbDodajNovuStavkuActionPerformed

    private void jbObrisiStavkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbObrisiStavkuActionPerformed
        kfn.obrisiStavku(jtStavkeNarudzbenice);
    }//GEN-LAST:event_jbObrisiStavkuActionPerformed

    private void jbZapamtiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbZapamtiActionPerformed
        kfn.zapamtiNarudzbenicu(jtfSifraNarudzbeniceKupca, jtfDatum, jtfKupac, jtStavkeNarudzbenice, narudzbenica, mod, this);
    }//GEN-LAST:event_jbZapamtiActionPerformed

    private void jbOdustaniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbOdustaniActionPerformed
        dispose();
    }//GEN-LAST:event_jbOdustaniActionPerformed

    private void jtStavkeNarudzbenicePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jtStavkeNarudzbenicePropertyChange
        int red = jtStavkeNarudzbenice.getSelectedRow();
        int kolona = jtStavkeNarudzbenice.getSelectedColumn();
        if (red != -1 && kolona == 4) {
            double cena = Double.parseDouble(jtStavkeNarudzbenice.getValueAt(red, 3) + "");
            double kolicina = Integer.parseInt(jtStavkeNarudzbenice.getValueAt(red, 4) + "0") / 10;
            double suma = cena * kolicina;
            jtStavkeNarudzbenice.setValueAt(suma, red, 5);
        }
    }//GEN-LAST:event_jtStavkeNarudzbenicePropertyChange
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbDodajNovuStavku;
    private javax.swing.JButton jbObrisiStavku;
    private javax.swing.JButton jbOdustani;
    private javax.swing.JButton jbZapamti;
    private javax.swing.JTable jtStavkeNarudzbenice;
    private javax.swing.JTextField jtfDatum;
    private javax.swing.JTextField jtfKupac;
    private javax.swing.JTextField jtfSifraNarudzbeniceKupca;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JComboBox jcbProizvodi;

    private void srediFormu(int mod) {
        switch (mod) {
            case 1:
                break;
            case 2:
                jbZapamti.setText("Sacuvaj izmene");
                jtfDatum.setText(narudzbenica.getDatum());
                jtfKupac.setText(narudzbenica.getKupac());
                jtfSifraNarudzbeniceKupca.setText(narudzbenica.getSifraNarudzbeniceKup());
                kfn = new KontrolerFormeNarudzbenica(narudzbenica);

                for (int i = 0; i < narudzbenica.getStavkeNarudzbenice().size(); i++) {
                    StavkaNarudzbenice sn = ((StavkaNarudzbenice) narudzbenica.getStavkeNarudzbenice().get(i));
                    JComboBox jcbProiz = napraviJCBProizvodi();
                    int indexProiz = vratiIndexZeljenogProizvoda(jcbProiz, sn.getSifraProizvoda());
                    Proizvod p = (Proizvod) jcbProiz.getItemAt(indexProiz);
                    jcbProiz.setSelectedIndex(indexProiz);
                    sn.setNazivProizvoda(p.getNaziv());
                    sn.setCenaProizvoda(p.getCena());

                    System.out.println(p.vratiPodatkeKaoString());
                    Vector v = new Vector();
                    v.add(sn.getRedniBroj());
                    v.add(sn.getNazivProizvoda());
                    v.add(sn.getSifraProizvoda());
                    v.add(sn.getCenaProizvoda());
                    v.add(sn.getKolicina());
                    System.out.println(sn.getCenaProizvoda() * sn.getKolicina());
                    v.add(sn.getCenaProizvoda() * sn.getKolicina());
                    ((StavkaTableModel) jtStavkeNarudzbenice.getModel()).addRow(v);

                    TableColumn tc = jtStavkeNarudzbenice.getColumnModel().getColumn(1);

                    tc.setCellEditor(new DefaultCellEditor(jcbProiz));
                }
                break;
            default:
                jbZapamti.setText("Sacuvaj.");
                break;
        }
    }

    private JComboBox napraviJCBProizvodi() {
        JComboBox comboBox = new JComboBox();
        List proizvodi = kfn.vratiSveProizvode();
        Iterator it = proizvodi.iterator();
        while (it.hasNext()) {
            Proizvod p = (Proizvod) it.next();
            comboBox.addItem(p);
        }
        comboBox.addActionListener(this);
        return comboBox;
    }

    public int vratiIndexZeljenogProizvoda(JComboBox comboBox, int sifraProizvoda) {
        int brProizvoda = comboBox.getItemCount();
        for (int i = 0; i < brProizvoda; i++) {
            Proizvod p = (Proizvod) comboBox.getItemAt(i);
            if (p.getSifraProizvoda() == sifraProizvoda) {
                return i;
            }
        }
        return -1;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComboBox) {
            int kolona = jtStavkeNarudzbenice.getSelectedColumn();
            int red = jtStavkeNarudzbenice.getSelectedRow();
            if (kolona == 1) {//&& p != null) {
                Proizvod p = (Proizvod) ((StavkaTableModel) jtStavkeNarudzbenice.getModel()).getValueAt(red, kolona);
                System.out.println(p.vratiPodatkeKaoString());
                ((StavkaTableModel) jtStavkeNarudzbenice.getModel()).setValueAt(p.getSifraProizvoda(), red, kolona + 1);
                ((StavkaTableModel) jtStavkeNarudzbenice.getModel()).setValueAt(p.getCena(), red, kolona + 2);
                double cena = Double.parseDouble(jtStavkeNarudzbenice.getValueAt(red, 3) + "");
                double kolicina = Integer.parseInt(jtStavkeNarudzbenice.getValueAt(red, 4) + "0") / 10;
                double suma = cena * kolicina;
                jtStavkeNarudzbenice.setValueAt(suma, red, 5);
            }
        }
    }
}


