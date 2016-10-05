/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.kontrolerKorisnickogInterfejsa;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import com.cometoin.domenskeKlase.Proizvod;
import com.cometoin.domenskeKlase.Racun;
import com.cometoin.domenskeKlase.StavkaRacuna;
import com.cometoin.forme.komponente.StavkaTableModel;
import com.cometoin.klasaZaKomunikaciju.KlasaZaKomunikacijuKlijent;
import com.cometoin.provere.Provera;

/**
 *
 * @author user
 */
public class KontrolerFormeRacun {

    private int rbStavke = 1;
    int signal;

    public KontrolerFormeRacun() {
    }

    public KontrolerFormeRacun(Racun racun) {
        rbStavke = racun.getStavkeRacuna().size() + 1;
    }

    public void dodajStavku(JTable jtStavkeRacuna, JComboBox jcbProizvodi) {
        Vector v = new Vector();
        v.add(rbStavke);
        v.add("");
        v.add("");
        v.add(0);
        v.add("0");
        v.add(0);
        ((StavkaTableModel) jtStavkeRacuna.getModel()).addRow(v);

        //int red = jtStavkeNarudzbenice.getSelectedRow();
        TableColumn tc = jtStavkeRacuna.getColumnModel().getColumn(1);
        tc.setCellEditor(new DefaultCellEditor(jcbProizvodi));

        rbStavke++;
    }

    public void zapamtiRacun(JTextField jtfSifra, JTextField jtfDatum, JTextField jtfPartner, JTable jtStavkeRacuna, Racun racun, int mod, JDialog dialog) {
        int uspesnost = konvertujPodatkeIzKomponentiUObjekat(jtfSifra, jtfDatum, jtfPartner, jtStavkeRacuna, racun);
        switch (uspesnost) {
            case 1:
                JOptionPane.showMessageDialog(jtStavkeRacuna, "Neophodno je izabrati proizvod!");
                return;
            case 2:
                JOptionPane.showMessageDialog(jtStavkeRacuna, "Neophodno je uneti sifru narudzbenice kupca!");
                return;
            case 3:
                JOptionPane.showMessageDialog(jtStavkeRacuna, "Neispravan datum! dd-mm-gggg");
                return;
        }
        switch (mod) {
            case 1:
                signal = KlasaZaKomunikacijuKlijent.getInstance().zapamtiRacun(racun);
                Provera.proveriSignal(jtStavkeRacuna, signal);
                break;
            case 2:
                signal = KlasaZaKomunikacijuKlijent.getInstance().zapamtiRacun(racun);
                Provera.proveriSignal(jtStavkeRacuna, signal);
                break;
        }
        dialog.dispose();
    }

    public void obrisiStavku(JTable jtStavkeRacuna) {
        if (rbStavke > 1) {
            ((StavkaTableModel) jtStavkeRacuna.getModel()).removeRow(rbStavke - 2);
            rbStavke--;
        }
    }

    public LinkedList vratiSveProizvode() {
        return KlasaZaKomunikacijuKlijent.getInstance().vratiProizvode(new Proizvod());
    }

    private int vratiStavkeIzTabele(LinkedList stavkeRacuna, JTable tabelaStavki, int sifraRacuna) {
        int uspesnost = 0;
        for (int i = 1; i <= tabelaStavki.getRowCount(); i++) {
            StavkaRacuna sr = new StavkaRacuna();
            sr.setSifra(sifraRacuna);
            int sifraProizvoda = Integer.valueOf(((StavkaTableModel) tabelaStavki.getModel()).getValueAt(i - 1, 0).toString());
            System.out.println(sifraProizvoda);

            sr.setRedniBroj(Integer.valueOf(((StavkaTableModel) tabelaStavki.getModel()).getValueAt(i - 1, 0).toString()));
            sr.setNazivProizvoda(((StavkaTableModel) tabelaStavki.getModel()).getValueAt(i - 1, 1).toString());
            if (sr.getNazivProizvoda().equals("")) {
                //0 - sve je ok; 1 - nije setovan naziv proizvoda
                return 1;
            }
            sr.setSifraProizvoda(Integer.parseInt(((StavkaTableModel) tabelaStavki.getModel()).getValueAt(i - 1, 2).toString()));
            sr.setCenaProizvoda(Double.valueOf(((StavkaTableModel) tabelaStavki.getModel()).getValueAt(i - 1, 3).toString()));
            sr.setKolicina(Integer.valueOf(((StavkaTableModel) tabelaStavki.getModel()).getValueAt(i - 1, 4).toString()));
            stavkeRacuna.add(sr);
        }
        return uspesnost;
    }

    private void izracunajUkupnuVrednost(Racun racun) {
        double ukupnaVrednost = 0;
        LinkedList lista = racun.getStavkeRacuna();
        Iterator it = lista.iterator();
        while (it.hasNext()) {
            StavkaRacuna sr = (StavkaRacuna) it.next();
            ukupnaVrednost += sr.getCenaProizvoda() * sr.getKolicina();
        }
        racun.setUkupnaVrednost(ukupnaVrednost);
    }

    private int konvertujPodatkeIzKomponentiUObjekat(JTextField jtfSifra, JTextField jtfDatum, JTextField jtfPartner, JTable jtStavkeRacuna, Racun racun) {
        String sifra = jtfSifra.getText();
        if (sifra.equals("")) {
            //0 - sve je ok; 2 - nije setovana sifra racuna
            return 2;
        }

        String datum = jtfDatum.getText();
        if (!proveriDatum(datum)) {
            //3 - datum nije dobar
            return 3;
        }

        String partner = jtfPartner.getText();
        LinkedList stavkeRacuna = new LinkedList();
        int uspesnost = vratiStavkeIzTabele(stavkeRacuna, jtStavkeRacuna, racun.getSifra());
        racun.setSifra(Integer.parseInt(sifra));
        racun.setDatum(datum);
        racun.setNazivPartnera(partner);
        racun.setStavkeRacuna(stavkeRacuna);
        izracunajUkupnuVrednost(racun);
        return uspesnost;
    }

    public void obrisiRacun(Racun racun, int mod, JDialog dialog) {
        switch (mod) {
            case 1:
                KlasaZaKomunikacijuKlijent.getInstance().obrisiRacun(racun);
                dialog.dispose();
                break;
            case 2:
                dialog.dispose();
                break;
            default:
                dialog.dispose();
        }
    }

    public int obradiRacun(Racun racun, JButton jbZapamti) {
        int n = JOptionPane.showConfirmDialog(
                null,
                "Da li ste sigurni da zelite da obradite ovaj racun: \n \t" + racun.getSifra() + "  " + "  ?\n Kasnije izmene nece biti moguce!",
                "Brisanje?",
                JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            jbZapamti.doClick();
            KlasaZaKomunikacijuKlijent.getInstance().obradiRacun(racun);
            JOptionPane.showMessageDialog(null, "Uspesno obradjen racun!");
            return 0;
        }
        return 1;
    }

    private boolean proveriDatum(String datum) {
        try {
            String dan = datum.substring(0, 2);
            String mesec = datum.substring(3, 5);
            String godina = datum.substring(6, datum.length());

            int d = Integer.parseInt(dan);
            int m = Integer.parseInt(mesec);
            int g = Integer.parseInt(godina);

            if (d > 31 || d < 1) {
                return false;
            }
            if (m < 1 || m > 12) {
                return false;
            }
            if (g < 1900 || g > 9999) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
