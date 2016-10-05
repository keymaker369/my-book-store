/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.sistemseOperacije;

import java.util.Iterator;
import java.util.LinkedList;

import com.cometoin.dbbr.DatabaseBroker;
import com.cometoin.domenskeKlase.Narudzbenica;
import com.cometoin.domenskeKlase.OpstiDomenskiObjekat;
import com.cometoin.domenskeKlase.StavkaNarudzbenice;

/**
 *
 * @author user
 */
public class ZapamtiNarudzbenicu extends OpstaSistemskaOperacija {

    public static int zapamtiNarudzbenicu(Narudzbenica narudzbenica) {
        ZapamtiNarudzbenicu zn = new ZapamtiNarudzbenicu();
        return zn.opsteIzvrsenjeSO(narudzbenica);
    }

    @Override
    public int izvrsenjeSistemskeOperacije(OpstiDomenskiObjekat odo) {

        //updejtuje se narudzbenica
        signal = DatabaseBroker.getInstance().updejtujSlog(odo);
        if (!proveriUspesnost()) {
            return signal;
        }

        if (!updejtujStavkeNarudzbenice(odo)) {
            return signal;
        }

        return 0;
    }

    @Override
    public boolean proveriUspesnost() {
        switch (signal) {
            case 0:
                return true;
            default:
                return false;
        }
    }

    private boolean updejtujStavkeNarudzbenice(OpstiDomenskiObjekat odo) {
        LinkedList stavke = ((Narudzbenica) odo).getStavkeNarudzbenice();
        Iterator it = stavke.iterator();
        boolean suObrisane = false; // mislim na stavke, zato sto ih prvo sve pobrisem pa ih ponovo upisem
        while (it.hasNext()) {
            StavkaNarudzbenice sn = (StavkaNarudzbenice) it.next();
            if (!suObrisane) {
                signal = DatabaseBroker.getInstance().obrisiSlog(sn);
                if (!proveriUspesnost()) {
                    return false;
                }
                suObrisane = true;
            }
            signal = DatabaseBroker.getInstance().pamtiSlog(sn);
            if (!proveriUspesnost()) {
                return false;
            }
        }
        return proveriUspesnost();
    }
}
