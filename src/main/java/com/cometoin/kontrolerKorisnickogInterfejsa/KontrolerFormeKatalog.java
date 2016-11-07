/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.kontrolerKorisnickogInterfejsa;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.cometoin.domenskeKlase.Proizvod;
import com.cometoin.forme.FormaProizvod;
import com.cometoin.forme.komponente.KatalogTableModel;
import com.cometoin.klasaZaKomunikaciju.KlasaZaKomunikacijuKlijent;
import com.cometoin.provere.Provera;

/**
 *
 * @author user
 */
public class KontrolerFormeKatalog {

    int signal;

    public void dodajProizvod(JTable jtKatalog, List listaProizvoda) {
        new FormaProizvod(null, true, 1, new Proizvod()).setVisible(true);
        osveziFormu(jtKatalog, listaProizvoda);
    }

    public void izmeniProizvod(JTable jtKatalog, List listaProizvoda) {
        int selRed = jtKatalog.getSelectedRow();
        if (selRed != -1) {
            Proizvod p = ((KatalogTableModel) jtKatalog.getModel()).vratiProizvod(selRed);
            new FormaProizvod(null, true, 2, p).setVisible(true);
            osveziFormu(jtKatalog, listaProizvoda);
        } else {
            JOptionPane.showMessageDialog(jtKatalog, "Neophodno je izabrati proizvod!");
        }
    }

    public void obrisiProizvod(JTable jtKatalog, List listaProizvoda) {
        int selRed = jtKatalog.getSelectedRow();
        if (selRed == -1) {
            JOptionPane.showMessageDialog(jtKatalog, "Neophodno je izabrati proizvod!");
            return;
        }
        Proizvod p = ((KatalogTableModel) jtKatalog.getModel()).vratiProizvod(selRed);
        int n = JOptionPane.showConfirmDialog(
                null,
                "Da li ste sigurni da zelite da obrisete ovaj proizvod: \n" + p.getSifraProizvoda() + "  " + p.getNaziv() + "  ?",
                "Brisanje?",
                JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            signal = KlasaZaKomunikacijuKlijent.getInstance().obrisiProizvod(p);
            Provera.proveriSignal(jtKatalog, signal);
        }
        osveziFormu(jtKatalog, listaProizvoda);
    }

    private void osveziFormu(JTable jtKatalog, List listaProizvoda) {
        listaProizvoda = KlasaZaKomunikacijuKlijent.getInstance().vratiProizvode(new Proizvod());
        jtKatalog.setModel(new KatalogTableModel(listaProizvoda));
    }

    public void pronadjiProizvode(JTextField jtfPronadji, JTable jtKatalog, List listaProizvoda) {
        Proizvod p = new Proizvod();
        p.setNaziv(jtfPronadji.getText());
        listaProizvoda = KlasaZaKomunikacijuKlijent.getInstance().vratiPojedineProizvode(p);
        jtKatalog.setModel(new KatalogTableModel(listaProizvoda));
    }
}
