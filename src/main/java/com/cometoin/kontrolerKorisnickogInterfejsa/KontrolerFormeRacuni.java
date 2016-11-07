/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.kontrolerKorisnickogInterfejsa;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.cometoin.domenskeKlase.Racun;
import com.cometoin.forme.FormaRacun;
import com.cometoin.forme.komponente.RacuniTableModel;
import com.cometoin.klasaZaKomunikaciju.KlasaZaKomunikacijuKlijent;
import com.cometoin.provere.Provera;

/**
 *
 * @author user
 */
public class KontrolerFormeRacuni {

    int signal;

    public void dodajNoviRacun(JTable jtRacuni, List listaRacuna) {
        Racun noviRacun = new Racun();
        KlasaZaKomunikacijuKlijent.getInstance().kreirajRacun(noviRacun);
        if(signal!=0)
            Provera.proveriSignal(jtRacuni, signal);
        new FormaRacun(null, true, 1, noviRacun).setVisible(true);
        osveziFormu(jtRacuni, listaRacuna);
    }

    public void izmeniRacun(JTable jtRacuni, List listaRacuna) {
        int selRed = jtRacuni.getSelectedRow();
        if (selRed != -1) {
            Racun r = ((RacuniTableModel) jtRacuni.getModel()).vratiRacun(selRed);
            new FormaRacun(null, true, 2, r).setVisible(true);
            osveziFormu(jtRacuni, listaRacuna);
        } else {
            JOptionPane.showMessageDialog(jtRacuni, "Neophodno je izabrati racun!");
        }
    }

    public void obrisiRacun(JTable jtRacuni, List listaRacuna) {
        int selRed = jtRacuni.getSelectedRow();
        if (selRed == -1) {
            JOptionPane.showMessageDialog(jtRacuni, "Neophodno je izabrati racun!");
            return;
        }
        Racun r = ((RacuniTableModel) jtRacuni.getModel()).vratiRacun(selRed);
        int rez = JOptionPane.showConfirmDialog(
                null,
                "Da li ste sigurni da zelite da obrisete ovaj racun: \n  Sifra racuna - " + r.getSifra() + "  ?",
                "Brisanje?",
                JOptionPane.YES_NO_OPTION);
        if (rez == 0) {
            signal = KlasaZaKomunikacijuKlijent.getInstance().obrisiRacun(r);
            Provera.proveriSignal(jtRacuni, signal);
        }
        osveziFormu(jtRacuni, listaRacuna);
    }

    public List vratiSveRacune() {
        List lista = KlasaZaKomunikacijuKlijent.getInstance().vratiRacune(new Racun());
        return lista;
    }

    private void osveziFormu(JTable jtRacuni, List listaRacuna) {
        listaRacuna = KlasaZaKomunikacijuKlijent.getInstance().vratiRacune(new Racun());
        jtRacuni.setModel(new RacuniTableModel(listaRacuna));
    }
}
