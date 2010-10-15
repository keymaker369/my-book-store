/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerKorisnickogInterfejsa;

import domenskeKlase.Narudzbenica;
import forme.FormaGlavna;
import forme.FormaKatalog;
import forme.FormaNarudzbenica;
import forme.FormaNarudzbenice;
import forme.FormaRacuni;
import klasaZaKomunikaciju.KlasaZaKomunikacijuKlijent;
import provere.Provera;

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
