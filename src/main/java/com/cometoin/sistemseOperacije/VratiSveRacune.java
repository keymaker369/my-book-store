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

/**
 *
 * @author user
 */
public class VratiSveRacune extends OpstaSistemskaOperacija{

    private static LinkedList racuni;

    public static LinkedList vratiSveRacune(Racun racun) {
        VratiSveRacune vsr = new VratiSveRacune();
        vsr.opsteIzvrsenjeSO(racun);
        return racuni;
    }

    @Override
    public int izvrsenjeSistemskeOperacije(OpstiDomenskiObjekat odo) {
        racuni = DatabaseBroker.getInstance().vratiListuSvihSlogova(odo);
        if (racuni == null) {
            return signal;
        }

        if (!napuniRacune(racuni)) {
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

    private boolean napuniRacune(LinkedList racuni) {
        Iterator it = racuni.iterator();
        while (it.hasNext()) {
            Racun r = (Racun) it.next();
            signal = DatabaseBroker.getInstance().napuniRacun(r);
        }
        return true;
    }
}
