/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.kontrolerKorisnickogInterfejsa;

import com.cometoin.domenskeKlase.Narudzbenica;
import com.cometoin.forme.FormaGlavna;
import com.cometoin.forme.FormaKatalog;
import com.cometoin.forme.FormaNarudzbenica;
import com.cometoin.forme.FormaNarudzbenice;
import com.cometoin.forme.FormaRacuni;
import com.cometoin.klasaZaKomunikaciju.KlasaZaKomunikacijuKlijent;
import com.cometoin.provere.Provera;

/**
 *
 * @author user
 */
public class KontrolerFormeGlavna {

    private int signal;

    public void dodajNarudzbenicu(FormaGlavna formaGlavna) {
        Narudzbenica narudzbenica = new Narudzbenica();
        signal = KlasaZaKomunikacijuKlijent.getInstance().kreirajNarudzbenicu(narudzbenica);
        Provera.proveriSignal(formaGlavna, signal);
        System.out.println(narudzbenica.getSifraNarudzbenice());

        new FormaNarudzbenica(formaGlavna, true, 1, narudzbenica).setVisible(true);
    }

    public void prikaziKatalog(FormaGlavna formaGlavna) {
        new FormaKatalog(formaGlavna, true).setVisible(true);
    }

    public void prikaziNarudzbenice(FormaGlavna formaGlavna) {
        new FormaNarudzbenice(formaGlavna, true).setVisible(true);
    }

    public void prikaziRacune(FormaGlavna formaGlavna) {
        new FormaRacuni(formaGlavna, true).setVisible(true);
    }
}
