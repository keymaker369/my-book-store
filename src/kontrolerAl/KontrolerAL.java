/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerAl;

import domenskeKlase.Narudzbenica;
import domenskeKlase.Proizvod;
import domenskeKlase.Racun;
import java.util.LinkedList;
import sistemseOperacije.KreirajNarudzbenicu;
import sistemseOperacije.KreirajNoviProizvod;
import sistemseOperacije.KreirajRacun;
import sistemseOperacije.ObradiRacun;
import sistemseOperacije.ObrisiNarudzbenicu;
import sistemseOperacije.ObrisiProizvod;
import sistemseOperacije.ObrisiRacun;
import sistemseOperacije.VratiPojedineProizvode;
import sistemseOperacije.VratiSveNarudzbenice;
import sistemseOperacije.VratiSveProizvode;
import sistemseOperacije.VratiSveRacune;
import sistemseOperacije.ZapamtiProizvod;
import sistemseOperacije.ZapamtiNarudzbenicu;
import sistemseOperacije.ZapamtiRacun;

/**
 *
 * @author user
 */
public class KontrolerAL {

    static KontrolerAL instance;

    public static KontrolerAL getInstance() {

        if (instance == null) {
            instance = new KontrolerAL();
        }
        return instance;
    }

    public int zapamtiProizvod(Proizvod proizvod) {
        return ZapamtiProizvod.zapamtiProizvod(proizvod);
    }

    public int obrisiProizvod(Proizvod proizvod) {
        return ObrisiProizvod.obrisiProizvod(proizvod);
    }

    public int kreirajNoviProizvod(Proizvod noviProizvod) {
        return KreirajNoviProizvod.kreirajNoviProizvod(noviProizvod);
    }

    public int kreirajNarudzbenicu(Narudzbenica narudzbenica) {
        return KreirajNarudzbenicu.kreirajNarudzbenicu(narudzbenica);
    }

    public int zapamtiNarudzbenicu(Narudzbenica narudzbenica) {
        return ZapamtiNarudzbenicu.zapamtiNarudzbenicu(narudzbenica);
    }

    public LinkedList vratiProizvode(Proizvod proizvod) {
        return VratiSveProizvode.vratiSveProizvode(proizvod);
    }

    public LinkedList vratiNarudzbenice(Narudzbenica narudzbenica) {
        return VratiSveNarudzbenice.vratiSveNarudzbenice(narudzbenica);
    }

    public int obrisiNarudzbenicu(Narudzbenica narudzbenica) {
        return ObrisiNarudzbenicu.obrisiNarudzbenicu(narudzbenica);
    }

    public LinkedList vratiRacune(Racun racun) {
        return VratiSveRacune.vratiSveRacune(racun);
    }

    public int kreirajRacun(Racun racun) {
        return KreirajRacun.kreirajRacun(racun);
    }

    public int zapamtiRacun(Racun racun) {
        return ZapamtiRacun.zapamtiRacun(racun);
    }

    public int obrisiRacun(Racun racun) {
        return ObrisiRacun.obrisiRacun(racun);
    }

    public int obradiRacun(Racun racun) {
        return ObradiRacun.obradiRacun(racun);
    }

    public LinkedList vratiPojedineProizvode(Proizvod proizvod){
        return VratiPojedineProizvode.vratiPojedineProizvode(proizvod);
    }
}
