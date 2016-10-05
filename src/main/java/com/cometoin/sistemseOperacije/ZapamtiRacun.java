/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.sistemseOperacije;

import java.util.Iterator;
import java.util.LinkedList;

import com.cometoin.dbbr.DatabaseBroker;
import com.cometoin.domenskeKlase.OpstiDomenskiObjekat;
import com.cometoin.domenskeKlase.Racun;
import com.cometoin.domenskeKlase.StavkaRacuna;

/**
 *
 * @author user
 */
public class ZapamtiRacun extends OpstaSistemskaOperacija {

    public static int zapamtiRacun(Racun racun) {
        ZapamtiRacun zr = new ZapamtiRacun();
        return zr.opsteIzvrsenjeSO(racun);
    }

    @Override
    public int izvrsenjeSistemskeOperacije(OpstiDomenskiObjekat odo) {

        if (!proveriPreduslov(odo)) {
            return signal;
        }

        //updejtuje se racun
        signal = DatabaseBroker.getInstance().updejtujSlog(odo);
        if (!proveriUspesnost()) {
            return signal;
        }

        if (!updejtujStavkeRacuna(odo)) {
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

    private boolean updejtujStavkeRacuna(OpstiDomenskiObjekat odo) {
        LinkedList stavke = ((Racun) odo).getStavkeRacuna();
        if (stavke == null) {
            return true;
        }
        Iterator it = stavke.iterator();
        boolean suObrisane = false; // mislim na stavke, zato sto ih prvo sve pobrisem pa ih ponovo upisem
        while (it.hasNext()) {
            StavkaRacuna sr = (StavkaRacuna) it.next();
            if (!suObrisane) {
                signal = DatabaseBroker.getInstance().obrisiSlog(sr);
                if (!proveriUspesnost()) {
                    return false;
                }
                suObrisane = true;
            }
            signal = DatabaseBroker.getInstance().pamtiSlog(sr);
            if (!proveriUspesnost()) {
                return false;
            }
        }
        return proveriUspesnost();
    }

    private boolean proveriPreduslov(OpstiDomenskiObjekat odo) {
        int rezultatPretrage = DatabaseBroker.getInstance().daLiPostojiSlog(odo);
        if (!(rezultatPretrage == 0)) {
            return false;
        }

        Racun stariRacun = new Racun();
        stariRacun.setSifra(((Racun) odo).getSifra());
        signal = DatabaseBroker.getInstance().vratiSlog(stariRacun);
        if (!proveriUspesnost()) {
            return false;
        }

        if (stariRacun.isObradjen()) {
            return false;
        }

        return true;
    }
}
