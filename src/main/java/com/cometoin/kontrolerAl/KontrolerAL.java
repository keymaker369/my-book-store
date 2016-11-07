/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.kontrolerAl;

import java.util.LinkedList;
import java.util.List;

import com.cometoin.domenskeKlase.Narudzbenica;
import com.cometoin.domenskeKlase.Proizvod;
import com.cometoin.domenskeKlase.Racun;
import com.cometoin.sistemseOperacije.KreirajNarudzbenicu;
import com.cometoin.sistemseOperacije.KreirajNoviProizvod;
import com.cometoin.sistemseOperacije.KreirajRacun;
import com.cometoin.sistemseOperacije.ObradiRacun;
import com.cometoin.sistemseOperacije.ObrisiNarudzbenicu;
import com.cometoin.sistemseOperacije.ObrisiProizvod;
import com.cometoin.sistemseOperacije.ObrisiRacun;
import com.cometoin.sistemseOperacije.VratiPojedineProizvode;
import com.cometoin.sistemseOperacije.VratiSveNarudzbenice;
import com.cometoin.sistemseOperacije.VratiSveProizvode;
import com.cometoin.sistemseOperacije.VratiSveRacune;
import com.cometoin.sistemseOperacije.ZapamtiNarudzbenicu;
import com.cometoin.sistemseOperacije.ZapamtiProizvod;
import com.cometoin.sistemseOperacije.ZapamtiRacun;

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

    public List vratiProizvode(Proizvod proizvod) {
        return VratiSveProizvode.vratiSveProizvode(proizvod);
    }

    public List vratiNarudzbenice(Narudzbenica narudzbenica) {
        return VratiSveNarudzbenice.vratiSveNarudzbenice(narudzbenica);
    }

    public int obrisiNarudzbenicu(Narudzbenica narudzbenica) {
        return ObrisiNarudzbenicu.obrisiNarudzbenicu(narudzbenica);
    }

    public List vratiRacune(Racun racun) {
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
