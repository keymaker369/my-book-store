/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.sistemseOperacije;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.cometoin.dbbr.DatabaseBroker;
import com.cometoin.domenskeKlase.OpstiDomenskiObjekat;
import com.cometoin.domenskeKlase.Racun;
import com.cometoin.domenskeKlase.StavkaRacuna;

/**
 *
 * @author user
 */
public class KreirajRacun extends OpstaSistemskaOperacija {

    public static int kreirajRacun(Racun racun) {
        KreirajRacun kn = new KreirajRacun();
        return kn.opsteIzvrsenjeSO(racun);
    }

    @Override
    public int izvrsenjeSistemskeOperacije(OpstiDomenskiObjekat odo) {

        signal = DatabaseBroker.getInstance().vratiZadnjiSlog(odo);
        if (!proveriUspesnost()) {
            return signal;
        }
//        ((Racun) odo).setSifra(((Racun) odo).getSifra() + 1);


        signal = DatabaseBroker.getInstance().pamtiSlog(odo);
        if (!proveriUspesnost()) {
            return signal;
        }

        if (!kreirajStavkeRacuna(odo)) {
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

    private boolean kreirajStavkeRacuna(OpstiDomenskiObjekat odo) {
        List stavke = ((Racun) odo).getStavkeRacuna();
        if (stavke == null) {
            return proveriUspesnost();
        }
        
        Iterator it = stavke.iterator();
        while (it.hasNext()) {
            StavkaRacuna sr = (StavkaRacuna) it.next();
            sr.setSifra(((Racun) odo).getSifra());
            signal = DatabaseBroker.getInstance().pamtiSlog(sr);
        }
        return proveriUspesnost();
    }
}
