/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.kontrolerKorisnickogInterfejsa;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.cometoin.domenskeKlase.Narudzbenica;
import com.cometoin.forme.FormaNarudzbenica;
import com.cometoin.forme.komponente.NarudzbeniceTableModel;
import com.cometoin.klasaZaKomunikaciju.KlasaZaKomunikacijuKlijent;
import com.cometoin.provere.Provera;

/**
 *
 * @author user
 */
public class KontrolerFormeNarudzbenice {

    int signal;

    public void dodajNovuNarudzbenicu(JTable jtNarudzbenice, List listaNarudzbenica) {
        new FormaNarudzbenica(null, true, 1, new Narudzbenica()).setVisible(true);
        osveziFormu(jtNarudzbenice, listaNarudzbenica);
    }

    public void izmeniNarudzbenicu(JTable jtNarudzbenice, List listaNarudzbenica) {
        int selRed = jtNarudzbenice.getSelectedRow();
        if (selRed != -1) {
            Narudzbenica n = ((NarudzbeniceTableModel) jtNarudzbenice.getModel()).vratiNarudzbenicu(selRed);
            new FormaNarudzbenica(null, true, 2, n).setVisible(true);
            osveziFormu(jtNarudzbenice, listaNarudzbenica);
        } else {
            JOptionPane.showMessageDialog(jtNarudzbenice, "Neophodno je izabrati narudzbenicu!");
        }
    }

    public void obrisiNarudzbenicu(JTable jtNarudzbenice, List listaNarudzbenica) {
        int selRed = jtNarudzbenice.getSelectedRow();
        if (selRed == -1) {
            JOptionPane.showMessageDialog(jtNarudzbenice, "Neophodno je izabrati narudzbenicu!");
            return;
        }
        Narudzbenica n = ((NarudzbeniceTableModel) jtNarudzbenice.getModel()).vratiNarudzbenicu(selRed);
        int rez = JOptionPane.showConfirmDialog(
                null,
                "Da li ste sigurni da zelite da obrisete ovu narudzbenicu: \n  Sifra narudzbenice - " + n.getSifraNarudzbenice() + "  ?",
                "Brisanje?",
                JOptionPane.YES_NO_OPTION);
        if (rez == 0) {
            signal = KlasaZaKomunikacijuKlijent.getInstance().obrisiNarudzbenicu(n);
            Provera.proveriSignal(jtNarudzbenice, signal);
        }
        osveziFormu(jtNarudzbenice, listaNarudzbenica);
    }

    public List vratiSveNarudzbenice() {
        List lista = KlasaZaKomunikacijuKlijent.getInstance().vratiNarudzbenice(new Narudzbenica());
        return lista;
    }

    private void osveziFormu(JTable jtNarudzbenice, List listaNarudzbenica) {
        listaNarudzbenica = KlasaZaKomunikacijuKlijent.getInstance().vratiNarudzbenice(new Narudzbenica());
        jtNarudzbenice.setModel(new NarudzbeniceTableModel(listaNarudzbenica));
    }
}
